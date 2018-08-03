package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.business.cart.operate;

import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.exchange.RabbitMQTopicExchange;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.queue.RabbitMQQueue;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.constant.RabbitMqConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 新增 购物车 队列 和绑定 配置
 * <pre>
 *     如果交换器通过路由键绑定多个队列，由需要创建多个binding方法（注：参数queue和exchange和实例的Queue和Exchange的方法名称相同）
 * </pre>
 */
@Configuration("QueueBindAddCartProducerConfiguration")
public class RabbitMQQueueBindAddCartProducerConfiguration {

    @Autowired
    private RabbitMQTopicExchange rabbitMQTopicExchange;

    @Autowired
    private RabbitMQQueue rabbitMQQueue;

    /**
     * 创建 RabbitMQ Queue 【operate】
     *
     * @param cartProducerRabbitAdmin 已申明购物车RabbitAdmin，此RabbitAdmin绑定了对应的ConnectionFactory
     * @return 已绑定指定申明（connectionFactory）的队列
     */
    @Bean("addCartProducerQueue")
    public Queue addCartQueue(RabbitAdmin cartProducerRabbitAdmin) {
        return rabbitMQQueue.createQueue(cartProducerRabbitAdmin, RabbitMqConfig.QUEUE_CART_ADD_NAME);
    }

    /**
     * 创建 RabbitMQ Binding 【binding by create】
     *
     * @param cartProducerRabbitAdmin 已申明购物车RabbitAdmin，此RabbitAdmin绑定了对应的ConnectionFactory
     * @param cartProducerExchange    已申请的交换机
     * @param addCartProducerQueue    已申请的新增队列
     * @return 已绑定指定申明（connectionFactory）的绑定
     */
    @Bean("bindingAddCart")
    public Binding bindingAddCart(RabbitAdmin cartProducerRabbitAdmin, TopicExchange cartProducerExchange, Queue addCartProducerQueue) {
        return rabbitMQTopicExchange.createBinding(cartProducerRabbitAdmin, cartProducerExchange, addCartProducerQueue, RabbitMqConfig.ROUTING_CART_ADD_KEY);
    }

}
