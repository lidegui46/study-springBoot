package ldg.study.springboot.designPattern.singleton;

import java.io.Serializable;

/**
 * 饿汉式 单例
 *
 * @author： 灿炉
 * @create date： 2019/6/27
 */
public class HungrySingleton implements Serializable {
    private static final HungrySingleton instance = new HungrySingleton();


    /**
     * 外部不可实例化
     */
    private HungrySingleton() {
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
    public static HungrySingleton getInstance() {
        return instance;
    }
}
