package ldg.study.springboot.messagequeue.rabbit.ack.consumer.constant;

/**
 * @author： ldg
 * @create date： 2018/7/23
 */
public class RabbitMqConfig {
    //创建订单 队列
    public final static String QUEUE_ORDER_CREATE_NAME = "customer.ack.queue.order.create";

    //支付订单 队列
    public final static String QUEUE_ORDER_PAY_NAME = "customer.ack.queue.order.pay";


    //添加到购物车 队列
    public final static String QUEUE_CART_ADD_NAME = "customer.ack.queue.cart.add";

    //删除购物车 队列
    public final static String QUEUE_CART_DELETE_NAME = "customer.ack.queue.cart.delete";
}
