package ldg.study.springboot.messagequeue.kafka.consumer.order.orderProcess;

import com.alibaba.fastjson.JSONObject;
import ldg.study.springboot.messagequeue.kafka.consumer.order.OrderDto;
import ldg.study.springboot.messagequeue.kafka.consumer.order.OrderProcess;
import org.springframework.stereotype.Service;

/**
 * 下单进程
 *
 * @author foursix
 * @since 2017/11/3
 */
@Service
public class CreateOrderProcess implements OrderProcess {
    @Override
    public Integer getOrderType() {
        return 1;
    }

    @Override
    public void execute(OrderDto cartDto) {
        System.out.println("收到创建订单消息：" + JSONObject.toJSONString(cartDto));
    }
}
