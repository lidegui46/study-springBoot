package ldg.study.springboot.messagequeue.rabbit.ack.producer.support;

import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.template.RabbitMQTemplate;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.correlation.CorrelationType;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.constant.RabbitMqConfig;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.service.CartProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author： ldg
 * @create date： 2018/7/23
 */
@Service
public class CartProducerSupport implements CartProducerService {

    @Autowired
    @Qualifier("cartProducerRabbitTemplate")
    private RabbitTemplate rabbitConnectionTemplate;

    @Autowired
    private RabbitMQTemplate rabbitMQTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public void sendMessageByAddCart(Object customParam) {
        rabbitMQTemplate.convertAndSend(rabbitConnectionTemplate
                , CorrelationType.producer_retry_key_cart_add
                , RabbitMqConfig.EXCHANGE_CART_NAME
                , RabbitMqConfig.ROUTING_CART_ADD_KEY
                , customParam);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sendMessageByDeleteCart(Object customParam) {
        rabbitMQTemplate.convertAndSend(rabbitConnectionTemplate
                , CorrelationType.producer_retry_key_cart_delete
                , RabbitMqConfig.EXCHANGE_CART_NAME
                , RabbitMqConfig.ROUTING_CART_DELETE_KEY
                , customParam);
    }
}
