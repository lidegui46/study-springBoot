package ldg.study.springboot.messagequeue.kafka.baseConfig.service;

/**
 * Kafka消费者
 *
 * @author
 * @since 2017-09-13
 */
public interface Consumer {

    /**
     * 订阅消息
     *
     * @param listener 消费者监听器
     * @param topics   订阅的主题
     */
    void subscribe(ConsumerListener listener, String... topics);

    /**
     * 退订消息
     *
     * @param topic 消息主题
     */
    @Deprecated
    void unsubscribe(String... topic);

    /**
     * 退订全部消息
     */
    void unsubscribeAll();
}
