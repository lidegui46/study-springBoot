package ldg.study.springboot.designPattern.mediator.collage;

import ldg.study.springboot.designPattern.mediator.mediator.Mediator;

/**
 * 提供方房源
 *
 * @author： 灿炉
 * @create date： 2019/5/8
 */
public class SupplyCollage extends Collage {
    public SupplyCollage(String name, Mediator mediator) {
        super(name, mediator);
    }

    @Override
    public void send(String msg) {
        this.mediator.pushSupply(msg);
    }

    @Override
    public void receive(String msg) {
        System.out.println(String.format("%s 通过中介 %s  : %s", name, this.mediator.getName(), msg));
    }
}