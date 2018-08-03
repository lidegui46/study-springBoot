package ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.business.order;

import ldg.study.springboot.messagequeue.rabbit.ack.consumer.constant.RabbitMqConfig;
import ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.business.ConsumeService;
import org.springframework.stereotype.Service;

/**
 * @author： ldg
 * @create date： 2018/7/25
 */
@Service(value = RabbitMqConfig.QUEUE_ORDER_PAY_NAME)
public class ConsumePayOrderSupport implements ConsumeService {

    @Override
    public void execute(String param) {
        System.out.println("consume execute message:\r\n   pay order\r\n   param : " + param);
    }
}
