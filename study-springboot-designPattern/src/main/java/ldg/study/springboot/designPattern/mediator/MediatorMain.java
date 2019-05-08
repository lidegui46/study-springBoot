package ldg.study.springboot.designPattern.mediator;

import ldg.study.springboot.designPattern.mediator.collage.BuyCollage;
import ldg.study.springboot.designPattern.mediator.collage.Collage;
import ldg.study.springboot.designPattern.mediator.collage.SupplyCollage;
import ldg.study.springboot.designPattern.mediator.mediator.HouseMediator;

/**
 * 入口
 *
 * @author： 灿炉
 * @create date： 2019/5/8
 */
public class MediatorMain {
    public static void main(String[] args) {
        HouseMediator houseMediator = new HouseMediator("张三中介所");
        Collage buyCollage = new BuyCollage("张三租客", houseMediator);
        houseMediator.registerNeed(buyCollage);

        Collage sellerCollage = new SupplyCollage("李四房源", houseMediator);
        houseMediator.registerSupply(sellerCollage);

        buyCollage.send("租套三");
        sellerCollage.send("套三");
    }
}
