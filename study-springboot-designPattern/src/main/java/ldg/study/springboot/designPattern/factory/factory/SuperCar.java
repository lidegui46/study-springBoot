package ldg.study.springboot.designPattern.factory.factory;

import ldg.study.springboot.designPattern.factory.abstractFactory.Car;

/**
 * 速派 汽车
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class SuperCar extends Car {
    public SuperCar() {
        System.out.println("速派车上场");
    }

    @Override
    public void drive() {
        System.out.println("            开车了");
    }
}
