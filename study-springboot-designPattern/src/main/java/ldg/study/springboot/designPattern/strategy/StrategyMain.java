package ldg.study.springboot.designPattern.strategy;

/**
 * @author： ldg
 * @create date： 2019/2/21
 */
public class StrategyMain {
    public static void main(String[] args) {
        FlyStrategy flyStrategy = new HorizontalFlyStrategy();
        flyStrategy.fly();
        
        flyStrategy = new VerticalFlyStrategy();
        flyStrategy.fly();
    }
}
