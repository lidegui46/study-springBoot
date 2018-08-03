package ldg.study.springboot.messagequeue.kafka.producer.order.support;

/**
 * @author foursix
 * @since 2017/11/3
 */
public abstract class OrderKafkaProducer {
    /**
     * topic名称
     */
    protected static String topicName = "orderMsg";
}
