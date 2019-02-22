package ldg.study.springboot.designPattern.proxy.dynamicProxy.cglibProxy;

import ldg.study.springboot.designPattern.proxy.staticProxy.Car;

/**
 * @author： ldg
 * @create date： 2019/2/22
 */
public class CglibProxyMain {
    public static void main(String[] args) {
        Car car = (Car) new CarCglibProxy().getProxy(new Car());
        car.move();
    }
}
