package ldg.study.springboot.aop.ProxyFactory;

import org.aopalliance.aop.Advice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.cglib.core.DebuggingClassWriter;
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
        //输出 CGLIB 代理类文件到指定目录
        //CGLIB 代理类文件（3个）
        /*
            原理：读取“被代理类”的字段、构造方法、普通方法，通过ASM（字节码操作框架）生成CGLIB 代理类，代理类 继承了“被代理类”，
                  代理类添加了“被代理类”的所有方法作为代理类的属性 和 普通方法
                  当通过代理对象调用普通方法时，调用的是代理类的普通方法，普通方法再通过 MethodInterceptor调用方法拦截器，然调用具体的类

                  可通过访问“creageProxy”方法，查看已生成的“代理类”文件（放在D:\code目录下），通过反编译工具 查看“代理类”的字节码代码
        */
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");

        // 1. 创建Enhancer对象
        Enhancer enhancer = new Enhancer();

        // 2. cglib创建代理, 对目标对象创建子对象
        enhancer.setSuperclass(target.getClass());

        // 3. 传入回调接口, 对目标增强
        enhancer.setCallback(interceptorCallback);

        return enhancer.create();
    }
}
