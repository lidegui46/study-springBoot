package ldg.study.springboot.messagequeue.kafka.baseConfig.service;

/**
 * Kafka消费者监听器
 *
 * @author
 * @since 2017-09-13
 */
public interface ConsumerListener {

    /**
     * 收到一条Kafka消息
     *
     * @param topic   消息主题
     * @param message 消息内容
     */
    void onMessage(String topic, String message);
}
