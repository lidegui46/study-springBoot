package ldg.study.springboot.messagequeue.rabbit.ack.producer.web.api;

import ldg.study.springboot.messagequeue.rabbit.ack.producer.service.CartProducerService;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.service.OrderProducerService;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.web.dto.CartDto;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.web.dto.DrugsGoodsDto;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.web.dto.DrugsOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author foursix
 * @since 2017/11/15
 */
@RestController
@RequestMapping(value = "/mq/rabbit/producer/cart")
public class RabbitMQCartProducerController {

    @Autowired
    private CartProducerService cartProducerService;

    /**
     * http://localhost:8084/mq/rabbit/producer/cart/index
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Index() {

        CartDto cartDto = new CartDto();
        cartDto.setId("Cart001");
        cartDto.setStatus("add");

        cartProducerService.sendMessageByAddCart(cartDto);

        cartDto.setId("Cart001");
        cartDto.setStatus("delete");

        cartProducerService.sendMessageByDeleteCart(cartDto);

        return "OK";
    }
}
