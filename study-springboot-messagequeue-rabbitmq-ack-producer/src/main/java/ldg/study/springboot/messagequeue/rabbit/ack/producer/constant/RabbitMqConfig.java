package ldg.study.springboot.messagequeue.rabbit.ack.producer.constant;

/**
 * @author： ldg
 * @create date： 2018/7/23
 */
public class RabbitMqConfig {
    //创建订单 路由键
    public final static String ROUTING_ORDER_CREATE_KEY = "customer.ack.routeKey.order.create";
    //创建订单 队列
    public final static String QUEUE_ORDER_CREATE_NAME = "customer.ack.queue.order.create";

    //支付订单 路由键
    public final static String ROUTING_ORDER_PAY_KEY = "customer.ack.routeKey.order.pay";
    //支付订单 队列
    public final static String QUEUE_ORDER_PAY_NAME = "customer.ack.queue.order.pay";

    //订单 交换器
    public final static String EXCHANGE_ORDER_NAME = "customer.ack.exchange.order";



    //添加商品到购物车 路由键
    public final static String ROUTING_CART_ADD_KEY = "customer.ack.routeKey.cart.add";
    //添加商品到购物车 队列
    public final static String QUEUE_CART_ADD_NAME = "customer.ack.queue.cart.add";

    //删除购物车商品 路由键
    public final static String ROUTING_CART_DELETE_KEY = "customer.ack.routeKey.cart.delete";
    //删除购物车商品 队列
    public final static String QUEUE_CART_DELETE_NAME = "customer.ack.queue.cart.delete";

    //购物车 交换器
    public final static String EXCHANGE_CART_NAME = "customer.ack.exchange.cart";
}
