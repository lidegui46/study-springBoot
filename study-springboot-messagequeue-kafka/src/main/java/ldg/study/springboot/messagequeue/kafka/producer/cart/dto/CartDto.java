package ldg.study.springboot.messagequeue.kafka.producer.cart.dto;

/**
 * @author foursix
 * @since 2017/11/3
 */
public class CartDto {
    /** 购买人 */
    private long buyerId;
    /** 商品编号 */
    private long skuId;
    /** 数量 */
    private int num;
    /** 操作 1:新增 2：删除 */
    private int opearte;

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
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

    public int getOpearte() {
        return opearte;
    }

    public void setOpearte(int opearte) {
        this.opearte = opearte;
    }
}
