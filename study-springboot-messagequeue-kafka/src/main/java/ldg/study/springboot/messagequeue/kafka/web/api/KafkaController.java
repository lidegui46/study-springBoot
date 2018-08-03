package ldg.study.springboot.messagequeue.kafka.web.api;

import ldg.study.springboot.messagequeue.kafka.producer.cart.support.AddCartKafkaProducer;
import ldg.study.springboot.messagequeue.kafka.producer.order.support.CreateOrderKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author foursix
 * @since 2017/11/3
 */
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    private AddCartKafkaProducer addCartKafkaProducer;

    @Autowired
    private CreateOrderKafkaProducer createOrderKafkaProducer;

    /**
     * 添加购物车 发送kafka消息
     *
     * @return
     */
    @RequestMapping(value = "/addCart", method = RequestMethod.GET)
    public String addCart() {
        addCartKafkaProducer.send();

        return "";
    }

    /**
     * 创建订单 发送kafka消息
     *
     * @return
     */
    @RequestMapping(value = "/createOrder", method = RequestMethod.GET)
    public String createOrder() {
        createOrderKafkaProducer.send();

        return "";
    }
}
