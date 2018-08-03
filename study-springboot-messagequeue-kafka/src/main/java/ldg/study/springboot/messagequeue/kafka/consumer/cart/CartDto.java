package ldg.study.springboot.messagequeue.kafka.consumer.cart;

/**
 * @author foursix
 * @since 2017/11/3
 */
public class CartDto {
    private int buyerId;
    private long skuId;
    private int num;
    private int operate;

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }
}
