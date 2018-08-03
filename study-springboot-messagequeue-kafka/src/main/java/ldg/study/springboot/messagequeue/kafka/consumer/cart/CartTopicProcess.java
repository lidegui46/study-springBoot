package ldg.study.springboot.messagequeue.kafka.consumer.cart;

import com.alibaba.fastjson.JSONObject;
import ldg.study.springboot.messagequeue.kafka.consumer.base.TopicProcess;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class CartTopicProcess implements TopicProcess, ApplicationContextAware {
    /**
     * 购物车类型进程Map
     */
    private final Map<Integer, CartProcess> cartProcessMap = new HashMap<>();

    @Override
    public String getTopicName() {
        return "cartTopic";
    }

    /**
     * 找到对应的类并处理
     * <p>如：增加的类型，减少的类型</p>
     *
     * @param message 消息内容
     */
    @Override
    public void execute(String message) {
        CartDto cartDto = JSONObject.parseObject(message, CartDto.class);
        if ((!ObjectUtils.isEmpty(cartDto)) && (!CollectionUtils.isEmpty(cartProcessMap))) {
            CartProcess cartProcess = cartProcessMap.get(cartDto.getOperate());
            if (!ObjectUtils.isEmpty(cartProcess)) {
                cartProcess.execute(cartDto);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, CartProcess> beansOfType = applicationContext.getBeansOfType(CartProcess.class);
        if (!CollectionUtils.isEmpty(beansOfType)) {
            for (Map.Entry<String, CartProcess> entry : beansOfType.entrySet()) {
                if (!cartProcessMap.containsKey(entry.getValue().getOrderType())) {
                    cartProcessMap.put(entry.getValue().getOrderType(), entry.getValue());
                }
            }
        }
    }
}
