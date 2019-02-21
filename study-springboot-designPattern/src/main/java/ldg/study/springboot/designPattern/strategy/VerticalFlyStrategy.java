package ldg.study.springboot.designPattern.strategy;

/**
 * @author： ldg
 * @create date： 2019/2/21
 */
public class VerticalFlyStrategy implements FlyStrategy {
    @Override
    public void fly() {
        System.out.println("垂直 飞行");
    }
}
