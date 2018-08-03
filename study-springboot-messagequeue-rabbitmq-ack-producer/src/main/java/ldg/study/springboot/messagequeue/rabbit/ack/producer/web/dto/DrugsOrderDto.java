package ldg.study.springboot.messagequeue.rabbit.ack.producer.web.dto;

import java.util.List;

/**
 * @author： ldg
 * @create date： 2018/7/25
 */
public class DrugsOrderDto extends OrderDto{
    private List<DrugsGoodsDto> drugsGoods;

    public List<DrugsGoodsDto> getDrugsGoods() {
        return drugsGoods;
    }

    public void setDrugsGoods(List<DrugsGoodsDto> drugsGoods) {
        this.drugsGoods = drugsGoods;
    }
}
