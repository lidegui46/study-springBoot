package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.correlation;

/**
 * 业务操作类型
 *
 * @author： ldg
 * @create date： 2018/7/27
 */
public enum CorrelationType {
    producer_retry_key_order_create,
    producer_retry_key_order_pay,
    producer_retry_key_cart_add,
    producer_retry_key_cart_delete,
}
