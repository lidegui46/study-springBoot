package ldg.study.springboot.designPattern.factory.factory;

/**
 * 武汉汽车工厂
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class SuTenCarFactory extends CarFactory {

    @Override
    public Car create() {
        return new SuTenCar();
    }
}
