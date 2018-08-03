package ldg.study.springboot.aop.ProxyFactory;

import org.aopalliance.aop.Advice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 动态代理方式实现AOP
 * <p>
 * author：      ldg
 * create date： 2018/4/17
 */
public class CGLibProxyFactory {
    private Object target;

    public CGLibProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 环绕通知方式
     */
    private Callback interceptorCallback = new MethodInterceptor() {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("---------------- Before Advice ----------------");

            //执行方法
            Object result = method.invoke(target, args);

            System.out.println("---------------- After Advice ----------------");

            return result;
        }
    };

    /**
     * 前置通知 方式
     */
    private Advice beforeAdvice = new MethodBeforeAdvice() {
        @Override
        public void before(Method method, Object[] args, Object obj) throws Throwable {
            Object result = method.invoke(target, args);
        }
    };

    public Object createProxy() {
        // 1. 创建Enhancer对象
        Enhancer enhancer = new Enhancer();

        // 2. cglib创建代理, 对目标对象创建子对象
        enhancer.setSuperclass(target.getClass());

        // 3. 传入回调接口, 对目标增强
        enhancer.setCallback(interceptorCallback);

        return enhancer.create();
    }
}
