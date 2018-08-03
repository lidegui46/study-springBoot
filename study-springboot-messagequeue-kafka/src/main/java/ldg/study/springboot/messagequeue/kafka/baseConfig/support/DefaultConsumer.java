package ldg.study.springboot.messagequeue.kafka.baseConfig.support;

import ldg.study.springboot.messagequeue.kafka.baseConfig.DefaultConfig;
import ldg.study.springboot.messagequeue.kafka.baseConfig.service.ConsumerListener;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * Kafka消费者默认实现
 *
 * @author
 * @since 2017-09-13
 */
public class DefaultConsumer extends BaseKafka implements ldg.study.springboot.messagequeue.kafka.baseConfig.service.Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConsumer.class);

    private Properties properties;

    //========配置=========
    /**
     * 是否可以暂停
     */
    private static final boolean pauseEnable = true;

    /**
     * 尝试放入队列的持续时间
     */
    private static final long pauseAfterMs = 10000;

    /**
     * 队列长度
     */
    private static final int queueDepth = 1;

    /**
     * 如果无法poll下来数据，等待时间
     */
    private static final long pauseIdlTimeMs = 300;

    /**
     * 是否同步提交offset
     */
    private static final boolean enableSyncCommit = false;

    /**
     * 是否允许自动提交offset
     */
    private Boolean autoCommit = true;

    //=========消费者==========
    /**
     * kafka的consumer实例
     */
    private KafkaConsumer kafkaConsumer;

    private volatile ConsumerListener listener;

    private ConsumerRebalanceListener consumerRebalanceListener = new ConsumerRebalanceListenerDefault();

    private final OffsetCommitCallback offsetCommitCallback = (offsets, exception) -> {
        //TODO
    };

    //=========线程==========
    /**
     * 线程池
     */
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private QueueListenerInvoker queueListenerInvoker;

    private Future<?> pollFuture = null;

    //=========状态==========
    private volatile boolean running = false;

    private volatile boolean paused = false;

    private final ConcurrentHashMap<String, Map<Integer, Long>> offsets = new ConcurrentHashMap<>();

    private BlockingQueue<ConsumerRecords<String, String>> recordsToProcess = new LinkedBlockingQueue<>(queueDepth);

    private Collection<TopicPartition> assignedPartitions = new HashSet<>();

    private ConsumerRecords<String, String> unsent;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public DefaultConsumer() {

    }

    public DefaultConsumer(Properties properties) {
        this.properties = properties;

        try {
            initProperties();
            kafkaConsumer = new KafkaConsumer(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initProperties() throws IOException {
        if (properties == null) {
            this.loadPropertiesFromFile();
        }
        this.replaceProperties();
    }

    private void replaceProperties() {

        if (properties != null) {

            if (!StringUtils.hasText(properties.getProperty(ConsumerConfig.GROUP_ID_CONFIG))) {
                throw new IllegalStateException("消费者组" + ConsumerConfig.GROUP_ID_CONFIG + " 没有设置");
            }

            //是否自动提交已拉取消息的offset。提交offset即视为该消息已经成功被消费，该组下的Consumer无法再拉取到该消息（除非手动修改offset）。默认为true
            if (!StringUtils.hasText(properties.getProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG))) {
                properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, DefaultConfig.DEFAULT_ENABLE_AUTO_COMMIT);
            } else {
                autoCommit = Boolean.valueOf(properties.getProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG));
            }

            //自动提交offset的间隔毫秒数，默认5000。
            if (!StringUtils.hasText(properties.getProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG))) {
                properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, DefaultConfig.DEFAULT_AUTO_COMMIT_INTERVAL_MS);
            }

            //如果时间超过了,没有收到消费者的心跳,协调者标记消费者为死亡状态,并触发组中其他的消费者重新加入,来重新分配partitions
            if (!StringUtils.hasText(properties.getProperty(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG))) {
                properties.setProperty(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, DefaultConfig.DEFAULT_SESSION_TIMEOUT_MS);
            }

            //反序列化
            if (!StringUtils.hasText(properties.getProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG))) {
                properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, DefaultConfig.DEFAULT_DESERIALIZER);
            }
            if (!StringUtils.hasText(properties.getProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG))) {
                properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, DefaultConfig.DEFAULT_DESERIALIZER);
            }
        }
    }

    /*@PostConstruct
    public void initConsumer() {
        try {
            initProperties();
            kafkaConsumer = new KafkaConsumer(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 订阅消息
     *
     * @param listener 消费者监听器
     * @param topics   订阅的主题
     */
    @Override
    public void subscribe(final ConsumerListener listener, final String... topics) {

        //poll方法即是从Broker拉取消息，在poll之前首先要用subscribe方法订阅一个Topic。
        LOGGER.info("consumer pull topic:{} message begin...", Arrays.asList(topics));
        if (isRunning()) {
            //一个consumer只能订阅一次
            throw new IllegalStateException("同一个consumer只能订阅一次:" + Arrays.asList(topics) + "");
        } else {
            running = true;
        }
        //订阅
        kafkaConsumer.subscribe(Arrays.asList(topics), consumerRebalanceListener);
        this.listener = listener;
        if (isRunning()) {
            //分区topic情况
            assignedPartitions = kafkaConsumer.assignment();
        }
        //启动拉取线程
        pollFuture = executorService.submit(() -> {
            while (isRunning()) {
                try {
                    if (!autoCommit) {
                        commitIfNecessary();
                    }
                    //poll方法的入参是拉取超时毫秒数，如果没有新的消息可供拉取，consumer会等待指定的毫秒数，到达超时时间后会直接返回一个空的结果集。
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("kafkaConsumer.poll(1000)拉取消息partition={}，size={},recordsToProcess.size={},paused={},unsent={}", records.partitions(), records.count(), recordsToProcess.size(), paused, unsent == null ? 0 : unsent.count());
                    }
                    if (records != null && !records.isEmpty()) {
                        //如果拉取消息不为空
                        if (!autoCommit) {
                            //如果不自动提交
                            if (!sendToSolQueue(records)) {
                                //放入缓冲队列不成功
                                kafkaConsumer.pause(assignedPartitions.toArray(new TopicPartition[assignedPartitions.size()]));
                                LOGGER.info("kafka暂停消费成功，assignedPartitions={}", assignedPartitions.toString());
                                paused = true;
                                unsent = records;//存放
                            }
                        } else {
                            //如果autoCommit=true 则同步调用solMsg
                            solMsg(listener, records);
                        }
                        if (records.isEmpty() && paused) {
                            try {
                                Thread.sleep(pauseIdlTimeMs);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        //如果拉取消息为空
                        unsent = checkPause(unsent);
                    }
                } catch (WakeupException e) {
                    unsent = checkPause(unsent);
                } catch (Exception e) {
                    LOGGER.error("kafka consumer.poll feature while throws exception", e);
                }
            }
        });
        //启动异步处理消息线程
        if (!autoCommit) {
            startQueueListenerInvoker();
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutDown();
            }
        });
    }

    /**
     * 是否在运行状态
     *
     * @return
     */
    private boolean isRunning() {
        return running;
    }

    /**
     * 启动本地队列消费
     */
    private void startQueueListenerInvoker() {
        queueListenerInvoker = new QueueListenerInvoker(listener);
        executorService.submit(queueListenerInvoker);
    }

    /**
     * 停止本地队列消费
     */
    private boolean stopQueueListenerInvoker() {
        if (queueListenerInvoker != null) {
            queueListenerInvoker.stop();
            queueListenerInvoker = null;
            return true;
        }
        return false;
    }

    /**
     * 将消息放到队列中
     *
     * @param records
     * @return 是否放入成功
     */
    private boolean sendToSolQueue(final ConsumerRecords<String, String> records) {
        try {
            if (pauseEnable) {
                return this.recordsToProcess.offer(records, this.pauseAfterMs, TimeUnit.MILLISECONDS);
            } else {
                this.recordsToProcess.put(records);
                return true;
            }
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 检查是否可以恢复停顿状态
     *
     * @param unsent
     * @return
     */
    private ConsumerRecords<String, String> checkPause(ConsumerRecords<String, String> unsent) {
        if (this.paused && this.recordsToProcess.size() < this.queueDepth) {
            // Listener has caught up.
            kafkaConsumer.resume(assignedPartitions.toArray(new TopicPartition[assignedPartitions.size()]));
            LOGGER.info("kafka停顿恢复成功，assignedPartitions={}", assignedPartitions.toString());
            this.paused = false;
            if (unsent != null) {
                sendToSolQueue(unsent);
            }
            return null;
        }
        return unsent;
    }


    /**
     * 处理消息
     *
     * @param listener
     * @param records
     */
    private void solMsg(ConsumerListener listener, ConsumerRecords<String, String> records) {
        for (TopicPartition topicPartition : records.partitions()) {
            for (ConsumerRecord<String, String> record : records.records(topicPartition)) {
                //监听
                try {
                    listener.onMessage(record.topic(), record.value());
                    LOGGER.debug("fetched from partition " + record.partition() + ", offset: " + record.offset() + ", message: " + record.value());
                } catch (Exception e) {
                    LOGGER.error("listener.onMessage(topic,msg) throw exception,topic={},offset={},msg={},errorMsg=[{}]", record.topic(), record.offset(), record.value(), e.getMessage());
                } finally {
                    try {
                        addOffset(record);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    /**
     * 添加到偏移对象中
     *
     * @param record
     */
    private void addOffset(ConsumerRecord<String, String> record) {
        if (!this.offsets.containsKey(record.topic())) {
            this.offsets.put(record.topic(), new ConcurrentHashMap<Integer, Long>());
        }
        this.offsets.get(record.topic()).put(record.partition(), record.offset());
    }

    /**
     * 提交偏移量
     */
    private void commitIfNecessary() {
        Map<TopicPartition, OffsetAndMetadata> commits = new HashMap<>();
        for (Map.Entry<String, Map<Integer, Long>> entry : this.offsets.entrySet()) {
            for (Map.Entry<Integer, Long> offset : entry.getValue().entrySet()) {
                commits.put(new TopicPartition(entry.getKey(), offset.getKey()),
                        new OffsetAndMetadata(offset.getValue() + 1));
            }
        }
        this.offsets.clear();
        if (this.LOGGER.isDebugEnabled()) {
            this.LOGGER.debug("Commit list: " + commits);
        }
        if (!commits.isEmpty()) {
            if (this.LOGGER.isDebugEnabled()) {
                this.LOGGER.debug("Committing: " + commits);
            }
            try {
                if (this.enableSyncCommit) {
                    this.kafkaConsumer.commitSync(commits);
                } else {
                    this.kafkaConsumer.commitAsync(commits, DefaultConsumer.this.offsetCommitCallback);
                }
                LOGGER.info("提交offset成功;offset={}", commits.toString());
            } catch (WakeupException e) {
                // ignore - not polling
                if (this.LOGGER.isDebugEnabled()) {
                    this.LOGGER.debug("Woken up during commit");
                }
            }
        }
    }


    /**
     * 退订消息
     *
     * @param topics 消息主题集
     */
    @Override
    public void unsubscribe(String... topics) {
        LOGGER.info("consumer try to unsubscribe topic:{}", Arrays.asList(topics).toString());
        unsubscribeAll();
    }

    /**
     * 退订全部消息
     */
    @Override
    public void unsubscribeAll() {
        shutDown();
    }

    public void shutDown() {
        LOGGER.info("尝试停止kafka实例服务");
        running = false;
        if (pollFuture != null) {
            pollFuture.cancel(true);
            pollFuture = null;
        }
        stopQueueListenerInvoker();
        kafkaConsumer.wakeup();
        LOGGER.info("尝试停止kafka实例服务成功");
    }

    /**
     * 分区均衡时的回调
     */
    private class ConsumerRebalanceListenerDefault implements ConsumerRebalanceListener {
        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            DefaultConsumer.this.LOGGER.info("Received partition revocation notification,new partitions = {},stop the invoker and commit offset", partitions.toString());
            if (stopQueueListenerInvoker()) {
                recordsToProcess.clear();
                unsent = null;
            }
        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            DefaultConsumer.this.assignedPartitions = partitions;
            if (!DefaultConsumer.this.autoCommit) {
                // Commit initial positions - this is generally redundant but
                // it protects us from the case when another consumer starts
                // and rebalance would cause it to reset at the end
                // see https://github.com/spring-projects/spring-kafka/issues/110
                Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                for (TopicPartition partition : partitions) {
                    offsets.put(partition, new OffsetAndMetadata(kafkaConsumer.position(partition)));
                }
                DefaultConsumer.this.LOGGER.info("Committing: " + offsets);
                if (DefaultConsumer.this.enableSyncCommit) {
                    DefaultConsumer.this.kafkaConsumer.commitSync(offsets);
                } else {
                    DefaultConsumer.this.kafkaConsumer.commitAsync(offsets, DefaultConsumer.this.offsetCommitCallback);
                }
            }
            // We will not start the invoker thread if we are in autocommit mode,
            // as we will execute synchronously then
            // We will not start the invoker thread if the container is stopped
            // We will not start the invoker thread if there are no partitions to
            // listen to
            if (!DefaultConsumer.this.autoCommit && DefaultConsumer.this.isRunning()
                    && partitions != null && !partitions.isEmpty()) {
                startQueueListenerInvoker();
            }
        }
    }


    /**
     * 从本地分区中获取时候的回调
     */
    private class QueueListenerInvoker implements Runnable {

        private final ConsumerListener listener;

        public QueueListenerInvoker(ConsumerListener listener) {
            this.listener = listener;
        }

        private volatile boolean active = true;

        @Override
        public void run() {
            while (active) {
                try {
                    ConsumerRecords<String, String> records = recordsToProcess.poll(1000, TimeUnit.MILLISECONDS);
                    if (records != null) {
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("recordsToProcess.poll拉取消息partition={}，size={},recordsToProcess.size={}", records.partitions(), records.count(), recordsToProcess.size());
                        }
                        solMsg(listener, records);
                    } else {
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("recordsToProcess.poll未拉取到消息");
                        }
                    }
                } catch (InterruptedException e) {
                    if (!this.active) {
                        Thread.currentThread().interrupt();
                    } else {
                        LOGGER.debug("Interrupt ignored");
                    }
                } catch (Exception e) {
                    LOGGER.error("kafka recordsToProcess.poll feature while throws exception", e);
                }
            }
        }

        public void stop() {
            this.active = false;
        }
    }

    public void setConsumerRebalanceListener(ConsumerRebalanceListener consumerRebalanceListener) {
        this.consumerRebalanceListener = consumerRebalanceListener;
    }
}