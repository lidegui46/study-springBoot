package ldg.study.springboot.messagequeue.kafka.consumer.order.orderProcess;

import com.alibaba.fastjson.JSONObject;
import ldg.study.springboot.messagequeue.kafka.consumer.order.OrderDto;
import ldg.study.springboot.messagequeue.kafka.consumer.order.OrderProcess;
import org.springframework.stereotype.Service;

/**
 * 关闭订单进程
 *
 * @author foursix
 * @since 2017/11/3
 */
@Service
public class CloseOrderProcess implements OrderProcess {
    @Override
    public Integer getOrderType() {
        return 99;
    }

    @Override
    public void execute(OrderDto orderDto) {
        System.out.println("收到创建关闭消息：" + JSONObject.toJSONString(orderDto));
    }
}
