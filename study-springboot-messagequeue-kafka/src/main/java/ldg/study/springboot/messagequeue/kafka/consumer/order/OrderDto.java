package ldg.study.springboot.messagequeue.kafka.consumer.order;

/**
 * @author foursix
 * @since 2017/11/3
 */
public class OrderDto {
    private int status;
    private int buyerId;
    private String orderCode;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
