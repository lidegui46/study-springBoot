package ldg.study.springboot.messagequeue.kafka.consumer.cart;

/**
 * @author foursix
 * @since 2017/11/3
 */
public interface CartProcess {

    /**
     * 获取购物车类型
     *
     * @return
     */
    Integer getOrderType();

    /**
     * 执行
     *
     * @param cartDto 购物车信息
     */
    void execute(CartDto cartDto);
}
