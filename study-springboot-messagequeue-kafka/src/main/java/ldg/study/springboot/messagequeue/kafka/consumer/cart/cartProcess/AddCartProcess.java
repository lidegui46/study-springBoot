package ldg.study.springboot.messagequeue.kafka.consumer.cart.cartProcess;

import com.alibaba.fastjson.JSONObject;
import ldg.study.springboot.messagequeue.kafka.consumer.cart.CartDto;
import ldg.study.springboot.messagequeue.kafka.consumer.cart.CartProcess;
import org.springframework.stereotype.Service;

/**
 * 增加购物车进程
 *
 * @author foursix
 * @since 2017/11/3
 */
@Service
public class AddCartProcess implements CartProcess {
    @Override
    public Integer getOrderType() {
        return 1;
    }

    @Override
    public void execute(CartDto cartDto) {
        System.out.println("收到创建购物车新增商品数量消息：" + JSONObject.toJSONString(cartDto));
    }
}
