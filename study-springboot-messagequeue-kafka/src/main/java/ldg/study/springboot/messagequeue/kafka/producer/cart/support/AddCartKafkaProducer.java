package ldg.study.springboot.messagequeue.kafka.producer.cart.support;

import com.alibaba.fastjson.JSONObject;
import ldg.study.springboot.messagequeue.kafka.baseConfig.service.Producer;
import ldg.study.springboot.messagequeue.kafka.producer.cart.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author foursix
 * @since 2017/11/3
 */
@Component
public class AddCartKafkaProducer extends CartKafkaProducer {
    /**
     * 此注解在 KafkaConfiguration 中读取“ @Bean(name = "kafkaProducer") ”
     */
    @Autowired
    private Producer kafkaProducer;

    public void send() {
        CartDto cartDto = new CartDto();
        cartDto.setBuyerId(168);
        cartDto.setNum(1);
        cartDto.setSkuId(123);
        cartDto.setOpearte(1);

        kafkaProducer.publish(this.topicName, JSONObject.toJSONString(cartDto));
    }
}
