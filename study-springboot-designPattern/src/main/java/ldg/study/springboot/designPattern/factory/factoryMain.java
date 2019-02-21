package ldg.study.springboot.designPattern.factory;

import ldg.study.springboot.designPattern.factory.factory.Car;
import ldg.study.springboot.designPattern.factory.factory.Factory;
import ldg.study.springboot.designPattern.factory.factory.SuTenCarFactory;
import ldg.study.springboot.designPattern.factory.factory.SuperCarFactory;

/**
 * @author： ldg
 * @create date： 2019/2/21
 */
public class factoryMain {
    public static void main(String[] args) {

//        Factory factory = new SuperCarFactory();
        Factory factory = new SuTenCarFactory();
        Car car = factory.create();
        car.drive();
    }
}
