package ldg.study.springboot.messagequeue.kafka.baseConfig.support;


import ldg.study.springboot.messagequeue.kafka.baseConfig.DefaultConfig;
import ldg.study.springboot.messagequeue.kafka.baseConfig.service.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

/**
 * Kafka生产者默认实现
 *
 * @author
 * @since 2017-09-13
 */
public class DefaultProducer extends BaseKafka implements Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProducer.class);

    private KafkaProducer kafkaProducer;

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public DefaultProducer() {

    }

    public DefaultProducer(Properties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void initProducer() {
        try {
            initProperties();
            kafkaProducer = new KafkaProducer(properties);
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

            /*
            消息缓冲池大小。尚未被发送的消息会保存在Producer的内存中，如果消息产生的速度大于消息发送的速度，
            那么缓冲池满后发送消息的请求会被阻塞。默认33554432字节（32MB）
            */
            if (!StringUtils.hasText(properties.getProperty(ProducerConfig.BUFFER_MEMORY_CONFIG))) {
                properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, DefaultConfig.DEFAULT_BUFFER_MEMORY);
            }

            /*
            当同时有大量消息要向同一个分区发送时，Producer端会将消息打包后进行批量发送。
            如果设置为0，则每条消息都独立发送。默认为16384字节
            */
            if (!StringUtils.hasText(properties.getProperty(ProducerConfig.BATCH_SIZE_CONFIG))) {
                properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, DefaultConfig.DEFAULT_BATCH_SIZE);
            }

            /*
            发送消息前等待的毫秒数，与batch.size配合使用。
            在消息负载不高的情况下，配置linger.ms能够让Producer在发送消息前等待一定时间，
            以积累更多的消息打包发送，达到节省网络资源的目的。默认为0
             */
            if (!StringUtils.hasText(properties.getProperty(ProducerConfig.LINGER_MS_CONFIG))) {
                properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, DefaultConfig.DEFAULT_LINGER_MS);
            }

            //序列化
            if (!StringUtils.hasText(properties.getProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG))) {
                properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, DefaultConfig.DEFAULT_SERIALIZER);
            }
            if (!StringUtils.hasText(properties.getProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG))) {
                properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DefaultConfig.DEFAULT_SERIALIZER);
            }
        }
    }

    /**
     * 发布消息
     *
     * @param topic     消息主题
     * @param partition 消息分区
     * @param msg       消息内容
     */
    @Override
    public void publish(String topic, String partition, String msg) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, partition, msg);
        doPublish(record);
        LOGGER.debug("producer push topic:{},partition:{} ,message:{} is success", topic, partition, msg);
    }

    /**
     * 发布消息
     *
     * @param topic 消息主题
     * @param msg   消息内容
     */
    @Override
    public void publish(String topic, String msg) {
        final ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
        doPublish(record);
        LOGGER.debug("producer push topic:{},message:{} is success", topic, msg);
    }

    private void doPublish(ProducerRecord<String, String> record) {
        kafkaProducer.send(record, (metadata, e) -> {
            if (e != null) {
                LOGGER.error("producer push message callback is fail", e);
                e.printStackTrace();
            } else {
                LOGGER.debug("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());
            }
        });
    }

}
