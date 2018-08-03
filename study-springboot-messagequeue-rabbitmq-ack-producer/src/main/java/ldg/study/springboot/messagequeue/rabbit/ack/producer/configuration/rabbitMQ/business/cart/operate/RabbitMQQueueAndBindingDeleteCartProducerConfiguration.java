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
 * 删除 购物车 队列 和绑定 配置
 * <pre>
 *     如果交换器通过路由键绑定多个队列，由需要创建多个binding方法（注：参数queue和exchange和实例的Queue和Exchange的方法名称相同）
 * </pre>
 */
@Configuration("rabbitMQQueueAndBindingDeleteCartProducerConfiguration")
public class RabbitMQQueueAndBindingDeleteCartProducerConfiguration {
    @Autowired
    private RabbitMQTopicExchange rabbitMQTopicExchange;

    @Autowired
    private RabbitMQQueue rabbitMQQueue;

    /**
     * 创建 RabbitMQ Queue 【delete】
     *
     * @param cartProducerRabbitAdmin 已申明购物车RabbitAdmin，此RabbitAdmin绑定了对应的ConnectionFactory
     * @return 已绑定指定申明（connection）的队列
     */
    @Bean("deleteCartProducerQueue")
    public Queue deleteCartQueue(RabbitAdmin cartProducerRabbitAdmin) {
        return rabbitMQQueue.createQueue(cartProducerRabbitAdmin, RabbitMqConfig.QUEUE_CART_DELETE_NAME);
    }

    /**
     * 创建 RabbitMQ Binding 【binding by create】
     *
     * @param cartProducerRabbitAdmin 已申明购物车RabbitAdmin，此RabbitAdmin绑定了对应的ConnectionFactory
     * @param cartProducerExchange    已申请的交换机
     * @param deleteCartProducerQueue 已申请的删除队列
     * @return 已绑定指定申明（connection）的绑定
     */
    @Bean("bindingDeleteCart")
    public Binding bindingDeleteCart(RabbitAdmin cartProducerRabbitAdmin, TopicExchange cartProducerExchange, Queue deleteCartProducerQueue) {
        return rabbitMQTopicExchange.createBinding(cartProducerRabbitAdmin, cartProducerExchange, deleteCartProducerQueue, RabbitMqConfig.ROUTING_CART_DELETE_KEY);
    }
}
