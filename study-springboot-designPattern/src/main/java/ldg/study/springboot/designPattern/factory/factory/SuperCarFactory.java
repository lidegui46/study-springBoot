package ldg.study.springboot.designPattern.factory.factory;

/**
 * 成都汽车工厂
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class SuperCarFactory extends CarFactory {
    @Override
    public Car create() {
        return new SuperCar();
    }
}
