package ldg.study.springboot.messagequeue.rabbit.ack.producer.support;

import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.template.RabbitMQTemplate;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.correlation.CorrelationType;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.constant.RabbitMqConfig;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.service.OrderProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author： ldg
 * @create date： 2018/7/23
 */
@Service
public class OrderProducerSupport implements OrderProducerService {
    @Autowired
    @Qualifier("orderProducerRabbitTemplate")
    private RabbitTemplate rabbitConnectionTemplate;

    @Autowired
    private RabbitMQTemplate rabbitMQTemplate;

    @Override
    public void sendMessageByCreateOrder(Object customParam) {
        rabbitMQTemplate.convertAndSend(rabbitConnectionTemplate
                , CorrelationType.producer_retry_key_order_create
                , RabbitMqConfig.EXCHANGE_ORDER_NAME
                , RabbitMqConfig.ROUTING_ORDER_CREATE_KEY
                , customParam);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sendMessageByPayOrder(Object customParam) {
        rabbitMQTemplate.convertAndSend(rabbitConnectionTemplate
                , CorrelationType.producer_retry_key_order_pay
                , RabbitMqConfig.EXCHANGE_ORDER_NAME
                , RabbitMqConfig.ROUTING_ORDER_PAY_KEY
                , customParam);
    }
}
