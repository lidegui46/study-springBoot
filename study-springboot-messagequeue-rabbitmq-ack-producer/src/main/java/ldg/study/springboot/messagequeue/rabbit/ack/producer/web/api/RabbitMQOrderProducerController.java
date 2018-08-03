package ldg.study.springboot.messagequeue.rabbit.ack.producer.web.api;

import ldg.study.springboot.messagequeue.rabbit.ack.producer.service.OrderProducerService;
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
@RequestMapping(value = "/mq/rabbit/producer/order")
public class RabbitMQOrderProducerController {

    @Autowired
    private OrderProducerService orderProducerService;

    /**
     * http://localhost:8084/mq/rabbit/producer/order/index
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Index() {

        DrugsGoodsDto drugsGoodsDto = new DrugsGoodsDto();
        drugsGoodsDto.setId("drugId:001");
        drugsGoodsDto.setNum(10);
        drugsGoodsDto.setPrice(new BigDecimal(2));

        List<DrugsGoodsDto> drugsGoodsDtos = new ArrayList<>();
        drugsGoodsDtos.add(drugsGoodsDto);
        drugsGoodsDtos.add(drugsGoodsDto);

        DrugsOrderDto drugsOrderDto = new DrugsOrderDto();
        drugsOrderDto.setId("DD001");
        drugsOrderDto.setStatus("created");
        drugsOrderDto.setDrugsGoods(drugsGoodsDtos);

        orderProducerService.sendMessageByCreateOrder(drugsOrderDto);

        drugsOrderDto.setId("DD001");
        drugsOrderDto.setStatus("pay");

        orderProducerService.sendMessageByPayOrder(drugsOrderDto);

        return "OK";
    }
}
