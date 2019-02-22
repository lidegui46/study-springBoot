package ldg.study.springboot.designPattern.proxy.dynamicProxy.jdkProxy;

import ldg.study.springboot.designPattern.proxy.dynamicProxy.Moveable;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理 入口
 *
 * @author： ldg
 * @create date： 2019/2/22
 */
public class JdkMain {
    public static void main(String[] args) {
        RedCar car = new RedCar();
        CarHandler handler = new CarHandler(car);
        Moveable moveable = (Moveable) Proxy.newProxyInstance(car.getClass().getClassLoader(), car.getClass().getInterfaces(), handler);
        moveable.move();
    }
}
