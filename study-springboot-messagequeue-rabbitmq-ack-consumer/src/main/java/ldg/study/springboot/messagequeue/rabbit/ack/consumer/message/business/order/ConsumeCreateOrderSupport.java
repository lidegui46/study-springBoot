package ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.business.order;

import ldg.study.springboot.messagequeue.rabbit.ack.consumer.constant.RabbitMqConfig;
import ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.business.ConsumeService;
import org.springframework.stereotype.Service;

/**
 * 消费 创建订单
 *
 * @author： ldg
 * @create date： 2018/7/25
 */
@Service(value = RabbitMqConfig.QUEUE_ORDER_CREATE_NAME)
public class ConsumeCreateOrderSupport implements ConsumeService {

    @Override
    public void execute(String param) {
        System.out.println("consume execute message:\r\n   create order\r\n   param : " + param);
    }
}
