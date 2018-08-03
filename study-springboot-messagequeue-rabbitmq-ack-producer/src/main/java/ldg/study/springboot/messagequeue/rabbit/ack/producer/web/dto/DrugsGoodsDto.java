package ldg.study.springboot.messagequeue.rabbit.ack.producer.web.dto;

import java.math.BigDecimal;

/**
 * @author： ldg
 * @create date： 2018/7/25
 */
public class DrugsGoodsDto {
    private String id;
    private BigDecimal price;
    private int num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
