package ldg.study.springboot.designPattern.singleton;

import java.io.Serializable;

/**
 * 双重检查（Double-Check）
 * <pre>
 *  synchronized 和 volatile 共同保证了线程安全
 *      synchronized：保证只有一个线程进入
 *      volatile：阻止（singleton = new DoubleCheckSingleton()）内部[1-2-3]的指令重排，而是保证了在一个写操作（[1-2-3]）完成之前，不会调用读操作（if (instance == null)）
 * </pre>
 *
 * @author： ldg
 * @create date： 2018/12/24
 */
public class DoubleCheckSingleton implements Serializable {
    /**
     * 1、保证可见性
     * 2、禁止指令重排：它的赋值完成之（happens-before）前，不会调用读操作
     */
    private static volatile DoubleCheckSingleton instance;

    /**
     * 外部不可实例化
     */
    private DoubleCheckSingleton() {
        //为了防止反射
        if (instance != null) {
            throw new RuntimeException("别通过反射方式来序列化，没用");
            //do nothing
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

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}