package ldg.study.springboot.messagequeue.kafka.baseConfig.service;

/**
 * Kafka生产者
 *
 * @author
 * @since 2017-09-13
 */
public interface Producer {

    /**
     * 发布消息
     *
     * @param topic     消息主题
     * @param partition 消息分区
     * @param msg       消息内容
     */
    void publish(String topic, String partition, String msg);

    /**
     * 发布消息
     *
     * @param topic 消息主题
     * @param msg   消息内容
     */
    void publish(String topic, String msg);

}
