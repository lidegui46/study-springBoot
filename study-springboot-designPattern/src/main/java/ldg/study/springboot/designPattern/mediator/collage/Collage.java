package ldg.study.springboot.designPattern.mediator.collage;

import ldg.study.springboot.designPattern.mediator.mediator.Mediator;

/**
 * 组织
 *
 * @author： 灿炉
 * @create date： 2019/5/8
 */
public abstract class Collage {
    String name;
    Mediator mediator;

    Collage(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);
}
