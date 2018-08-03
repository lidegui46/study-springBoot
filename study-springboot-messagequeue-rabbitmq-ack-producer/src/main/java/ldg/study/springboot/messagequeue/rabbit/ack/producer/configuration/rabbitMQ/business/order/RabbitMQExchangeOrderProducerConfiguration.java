package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.business.order;

import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.exchange.RabbitMQTopicExchange;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.constant.RabbitMqConfig;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 订单 生产者 交换器 配置
 * <pre>
 *     如果交换器通过路由键绑定多个队列，由需要创建多个binding方法（注：参数queue和exchange和实例的Queue和Exchange的方法名称相同）
 * </pre>
 */
@Configuration("rabbitMQExchangeOrderProducerConfiguration")
public class RabbitMQExchangeOrderProducerConfiguration {
    @Autowired
    private RabbitMQTopicExchange rabbitMQTopicExchange;

    /**
     * 创建 RabbitMQ Exchange
     *
     * @param orderProducerRabbitAdmin 已申明订单RabbitAdmin，此RabbitAdmin绑定了对应的ConnectionFactory
     * @return 已绑定指定申明（connection）的交换机
     */
    @Bean("orderProducerExchange")
    public TopicExchange orderExchange(RabbitAdmin orderProducerRabbitAdmin) {
        return rabbitMQTopicExchange.createExchange(orderProducerRabbitAdmin, RabbitMqConfig.EXCHANGE_ORDER_NAME);
    }
}
