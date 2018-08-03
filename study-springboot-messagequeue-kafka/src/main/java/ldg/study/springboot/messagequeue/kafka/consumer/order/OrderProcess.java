package ldg.study.springboot.messagequeue.kafka.consumer.order;

/**
 * 订单进程
 *
 * @author foursix
 * @since 2017/11/3
 */
public interface OrderProcess {

    /**
     * 获取订单类型
     *
     * @return
     */
    Integer getOrderType();

    /**
     * 执行
     *
     * @param cartDto 订单信息
     */
    void execute(OrderDto cartDto);
}
