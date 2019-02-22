package ldg.study.springboot.designPattern.proxy.dynamicProxy.jdkProxy;

import ldg.study.springboot.designPattern.proxy.staticProxy.Car;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代码 处理器
 *
 * @author： ldg
 * @create date： 2019/2/22
 */
public class CarHandler implements InvocationHandler {

    private final RedCar car;

    public CarHandler(RedCar car) {
        this.car = car;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理 方法开始执行了。。。");
        Object invoke = method.invoke(car, args);
        System.out.println("jdk动态代理 方法执行结束了。。。");
        return invoke;
    }
}
