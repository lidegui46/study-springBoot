package ldg.study.springboot.designPattern.singleton;


import ldg.study.springboot.tools.utils.serialize.SerializableUtil;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * 内部类加载机制 - 单例械
 * <pre>
 *  原理：通过 jvm clasLoader 保证同步，线程安全
 *  类加载 知识点：
 *      1. new一个对象时
 *      2. 使用反射创建它的实例时
 *      3. 子类被加载时，如果父类还没被加载，就先加载父类
 *      4. jvm启动时执行的主类会首先被加载
 *      5. 一些其他的实现方式
 *          静态内部类：
 *              内部类SingletonHolder是一个饿汉式的单例实现，在SingletonHolder初始化的时候会由ClassLoader来保证同步，使INSTANCE是一个真单例。
 *              由于SingletonHolder是一个内部类，只在外部类的Singleton的getInstance()中被使用，所以它被加载的时机也就是在getInstance()方法第一次被调用的时候。
 * </pre>
 *
 * @author： ldg
 * @create date： 2018/12/24
 */
public class InnerClassSingleton implements Serializable {
    private static class SingletonHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    /**
     * 外部不可实例化
     */
    private InnerClassSingleton() {
        //为了防止反射
        if (SingletonHolder.INSTANCE != null) {
            throw new RuntimeException("别通过反射方式来序列化，没用");
        }
    }

    /**
     * 重写“序列化”读取解析
     * 目的：反序列化对象时，返回同一个实例化对象
     *
     * @return
     */
    protected Object readResolve() {
        return getInstance();
    }

    public static final InnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}