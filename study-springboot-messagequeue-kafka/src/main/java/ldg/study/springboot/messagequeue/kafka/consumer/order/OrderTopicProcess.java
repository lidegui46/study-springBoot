package ldg.study.springboot.messagequeue.kafka.consumer.order;

import com.alibaba.fastjson.JSONObject;
import ldg.study.springboot.messagequeue.kafka.consumer.base.TopicProcess;
import ldg.study.springboot.messagequeue.kafka.consumer.cart.CartDto;
import ldg.study.springboot.messagequeue.kafka.consumer.cart.CartProcess;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 购物车 Topic进程
 *
 * @author foursix
 * @since 2017/11/3
 */
@Service
public class OrderTopicProcess implements TopicProcess, ApplicationContextAware {
    /**
     * 购物车类型进程Map，一个订单同时只有一个状态，所以使用map
     */
    private final Map<Integer, OrderProcess> orderProcessMap = new HashMap<>();

    @Override
    public String getTopicName() {
        return "orderMsg";
    }

    /**
     * 找到对应的类并处理
     * <p>如：增加的类型，减少的类型</p>
     *
     * @param message 消息内容
     */
    @Override
    public void execute(String message) {
        OrderDto orderDto = JSONObject.parseObject(message, OrderDto.class);
        if ((!ObjectUtils.isEmpty(orderDto)) && (!CollectionUtils.isEmpty(orderProcessMap))) {
            OrderProcess orderProcess = orderProcessMap.get(orderDto.getStatus());
            if (!ObjectUtils.isEmpty(orderProcess)) {
                orderProcess.execute(orderDto);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, OrderProcess> beansOfType = applicationContext.getBeansOfType(OrderProcess.class);
        if (!CollectionUtils.isEmpty(beansOfType)) {
            for (Map.Entry<String, OrderProcess> entry : beansOfType.entrySet()) {
                if (!orderProcessMap.containsKey(entry.getKey())) {
                    orderProcessMap.put(entry.getValue().getOrderType(), entry.getValue());
                }
            }
        }
    }
}
