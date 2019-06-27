package ldg.study.springboot.designPattern.singleton;


import java.io.Serializable;

/**
 * 单例模式 - 枚举
 *
 * <pre>
 *      传统的两私有一公开（私有构造方法、私有静态实例(懒实例化/直接实例化)、公开的静态获取方法）涉及线程安全问题（即使有多重检查锁也可以通过反射破坏单例），
 *
 *      目前最为安全的实现单例的方法是通过内部静态enum的方法来实现，因为JVM会保证enum不能被反射并且构造器方法只执行一次。
 * </pre>
 *
 * <pre>
 *     枚举的不允许反射，详见 Constructor.newInstance() 的源码
 *     if ((clazz.getModifiers() & Modifier.ENUM) != 0)
 *             throw new IllegalArgumentException("Cannot reflectively create enum objects");
 * </pre>
 *
 * @author： ldg
 * @create date： 2018/12/24
 */
public enum EnumSingleton implements Serializable {
    INSTANCE;

    public static EnumSingleton getInstance() {
        return EnumSingleton.INSTANCE;
    }

    public void getData() {
    }
}
