package ldg.study.springboot.designPattern.proxy.dynamicProxy.cglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author： ldg
 * @create date： 2019/2/22
 */
public class CarCglibProxy implements MethodInterceptor {
    private Object target;

    public Object getProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(target.getClass());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib 动态代理开始了");
//        Object invoke = proxy.invoke(this.target, args);
        Object invoke = proxy.invokeSuper(obj, args);
        System.out.println("cglib 动态代理结束了");
        return invoke;
    }
}
