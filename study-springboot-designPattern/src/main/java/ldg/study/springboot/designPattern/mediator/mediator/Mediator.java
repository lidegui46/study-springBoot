package ldg.study.springboot.designPattern.mediator.mediator;

import ldg.study.springboot.designPattern.mediator.collage.Collage;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象中介者
 *
 * @author： 灿炉
 * @create date： 2019/5/8
 */
public abstract class Mediator {
    String name;

    public String getName() {
        return name;
    }

    Mediator(String name) {
        this.name = name;
    }

    //需求方
    List<Collage> needs = new ArrayList<>();

    public void registerNeed(Collage collage) {
        needs.add(collage);
    }

    public void pushNeed(String msg) {
        supplys.stream().forEach(p -> p.receive(msg));
    }

    //供应方
    List<Collage> supplys = new ArrayList<>();

    public void registerSupply(Collage collage) {
        supplys.add(collage);
    }

    public void pushSupply(String msg) {
        needs.stream().forEach(p -> p.receive(msg));
    }
}
