package ldg.study.springboot.messagequeue.kafka.producer.order.support;

import com.alibaba.fastjson.JSONObject;
import ldg.study.springboot.messagequeue.kafka.baseConfig.service.Producer;
import ldg.study.springboot.messagequeue.kafka.producer.cart.dto.CartDto;
import ldg.study.springboot.messagequeue.kafka.producer.order.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author foursix
 * @since 2017/11/3
 */
@Component
public class CreateOrderKafkaProducer extends OrderKafkaProducer {
    /**
     * 此注解在 KafkaConfiguration 中读取“ @Bean(name = "kafkaProducer") ”
     */
    @Autowired
    private Producer kafkaProducer;

    public void send() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerId(1);
        orderDto.setOrderCode("DD");
        orderDto.setStatus(1);

        kafkaProducer.publish(this.topicName, JSONObject.toJSONString(orderDto));
    }
}
