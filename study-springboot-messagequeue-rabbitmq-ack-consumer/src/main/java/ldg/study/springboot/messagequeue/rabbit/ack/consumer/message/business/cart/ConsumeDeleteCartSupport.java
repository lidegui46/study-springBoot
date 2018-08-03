package ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.business.cart;

import ldg.study.springboot.messagequeue.rabbit.ack.consumer.constant.RabbitMqConfig;
import ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.business.ConsumeService;
import org.springframework.stereotype.Service;

/**
 * 消费 删除购物车
 *
 * @author： ldg
 * @create date： 2018/7/25
 */
@Service(value = RabbitMqConfig.QUEUE_CART_DELETE_NAME)
public class ConsumeDeleteCartSupport implements ConsumeService {

    @Override
    public void execute(String param) {
        System.out.println("consume execute message:\r\n   delete cart\r\n   param : " + param);
    }
}
