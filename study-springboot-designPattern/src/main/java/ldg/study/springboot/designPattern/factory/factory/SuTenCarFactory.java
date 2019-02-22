package ldg.study.springboot.designPattern.factory.factory;

import ldg.study.springboot.designPattern.factory.abstractFactory.Car;
import ldg.study.springboot.designPattern.factory.abstractFactory.CarFactory;

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
