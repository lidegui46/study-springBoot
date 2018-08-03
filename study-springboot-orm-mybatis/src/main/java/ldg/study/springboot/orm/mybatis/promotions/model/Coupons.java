package ldg.study.springboot.orm.mybatis.promotions.model;

/**
 * @author foursix
 * @since 2017/11/15
 */
public class Coupons {
    private String couponCode;
    private Integer buyerId;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }
}
