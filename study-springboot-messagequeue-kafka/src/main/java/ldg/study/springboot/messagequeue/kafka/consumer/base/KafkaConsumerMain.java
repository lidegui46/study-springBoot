package ldg.study.springboot.messagequeue.kafka.consumer.base;

import ldg.study.springboot.messagequeue.kafka.baseConfig.service.Consumer;
import ldg.study.springboot.messagequeue.kafka.baseConfig.service.ConsumerListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * kafka消费主程序
 *
 * @author foursix
 * @since 2017/11/3
 */
@Component
public class KafkaConsumerMain implements ConsumerListener, ApplicationContextAware {
    /**
     * 一个主题可能被多个应用订阅，所以使用MultiValue
     */
    private final MultiValueMap<String, TopicProcess> topicProcessMultiValueMap = new LinkedMultiValueMap<>();

    /**
     * 接收消息
     *
     * @param kafkaConsumer kafka消费
     * @param topics        topics,以逗号分隔
     */
    public KafkaConsumerMain(Consumer kafkaConsumer, String topics) {
        subscribe(kafkaConsumer, topics);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, TopicProcess> beansOfType = applicationContext.getBeansOfType(TopicProcess.class);
        if (!CollectionUtils.isEmpty(beansOfType)) {
            for (Map.Entry<String, TopicProcess> entry : beansOfType.entrySet()) {
                if (topicProcessMultiValueMap.containsKey(entry.getKey())) {
                    if (topicProcessMultiValueMap.containsValue(entry.getValue())) {
                        continue;
                    }
                }
                topicProcessMultiValueMap.put(entry.getValue().getTopicName(), Collections.singletonList(entry.getValue()));
            }
        }
    }

    /**
     * 接收Kafka消息
     *
     * @param topic   消息主题
     * @param message 消息内容
     */
    @Override
    public void onMessage(String topic, String message) {
        List<TopicProcess> topicProcesses = topicProcessMultiValueMap.get(topic);
        if (!CollectionUtils.isEmpty(topicProcesses)) {
            topicProcesses.forEach(p -> p.execute(message));
        }
    }

    /**
     * 订阅消息
     *
     * @param kafkaConsumer kafka消费
     * @param topics        topics,以逗号分隔
     */
    public void subscribe(Consumer kafkaConsumer, String topics) {
        if (!ObjectUtils.isEmpty(kafkaConsumer)) {
            if (StringUtils.hasText(topics)) {
                String[] topicList = topics.split("\\,");
                if (topicList.length > 0) {
                    kafkaConsumer.subscribe(this, topicList);
                }
            }
        }
    }
}
