package ldg.study.springboot.designPattern.singleton;

import ldg.study.springboot.tools.utils.serialize.SerializableUtil;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * 校验单例的安全性
 *
 * @author： 灿炉
 * @create date： 2019/6/27
 */
public class CheckSingletonSecurity {
    static Class[] paremeterTypes = null;
    static Object[] initArgs = null;

    public static void main(String[] args) {
        checkDoubleCheckSingletonSecurity();
        checkLazySingletonSecurity();
        checkHungrySingletonSecurity();
        checkInnerClassSingletonSecurity();
        checkEnumSingletonSecurity();
    }

    /**
     * 校验 双检锁 安全
     */
    private static void checkDoubleCheckSingletonSecurity() {
        DoubleCheckSingleton singleton = DoubleCheckSingleton.getInstance();
        invokeSingleton("双检锁", DoubleCheckSingleton.class, singleton, paremeterTypes, initArgs);
    }

    /**
     * 校验 懒汉式 安全
     */
    private static void checkLazySingletonSecurity() {
        LazySingleton singleton = LazySingleton.getInstance();
        invokeSingleton("懒汉式", LazySingleton.class, singleton, paremeterTypes, initArgs);
    }

    /**
     * 校验 饿汉式 安全
     */
    private static void checkHungrySingletonSecurity() {
        HungrySingleton singleton = HungrySingleton.getInstance();
        invokeSingleton("饿汉式", HungrySingleton.class, singleton, paremeterTypes, initArgs);
    }

    /**
     * 校验 内部类加载机制 安全
     */
    private static void checkInnerClassSingletonSecurity() {
        InnerClassSingleton singleton = InnerClassSingleton.getInstance();
        invokeSingleton("内部类加载机制", InnerClassSingleton.class, singleton, paremeterTypes, initArgs);
    }

    /**
     * 校验 枚举 安全
     */
    private static void checkEnumSingletonSecurity() {
        paremeterTypes = new Class[]{String.class, int.class};
        initArgs = new Object[]{"test", 1};
        EnumSingleton singleton = EnumSingleton.getInstance();
        //EnumSingleton singleton = EnumSingleton.INSTANCE;
        invokeSingleton("枚举", EnumSingleton.class, singleton, paremeterTypes, initArgs);
    }

    private static void invokeSingleton(String title, Class<?> clazz, Object instance, Class<?>[] parameterTypes, Object[] initArgs) {
        Object singletonByReflect = getSingletonByReflect(clazz, parameterTypes, initArgs);
        Object singletonBySerializable = getSingletonBySerializable(instance);
        sout(title, instance, singletonByReflect, singletonBySerializable);
    }

    /**
     * 反射 实例化对象
     *
     * @param clazz    实例化的类
     * @param initArgs 实例化的参数
     * @param <T>
     * @return
     */
    private static <T> T getSingletonByReflect(Class<T> clazz, Class<?>[] parameterTypes, Object[] initArgs) {
        try {
            Constructor<?> declaredConstructor = parameterTypes == null ?
                    clazz.getDeclaredConstructor() : clazz.getDeclaredConstructor(parameterTypes);
            //把 private 改可以对外公布，调用
            declaredConstructor.setAccessible(true);
            return initArgs == null ?
                    (T) declaredConstructor.newInstance() : (T) declaredConstructor.newInstance(initArgs);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 序列化 实例化对象
     *
     * @param instance 原实例化对象
     * @return
     */
    private static Object getSingletonBySerializable(Object instance) {
        ByteArrayOutputStream byteArrayOutputStream = SerializableUtil.serializeToByteArray(instance);
        return SerializableUtil.deserializeByByteArray(byteArrayOutputStream);

        /*try {
            String fileStreamName = "serializableSingleton.ser";
            ObjectOutput output = new ObjectOutputStream(new FileOutputStream(fileStreamName));
            output.writeObject(instance);
            output.close();

            ObjectInput input = new ObjectInputStream(new FileInputStream(fileStreamName));
            Object serializableINstance = input.readObject();
            input.close();
            return serializableINstance;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }*/
    }

    /**
     * 输出 结论
     *
     * @param title               标题
     * @param instance            原单例 对象
     * @param reflectInstance     反射后的对象
     * @param serialiableInstance 序列化后的对象
     * @param <T>
     */
    private static <T> void sout(String title, T instance, T reflectInstance, T serialiableInstance) {
        int instanceHashCode = instance == null ? -1 : instance.hashCode();
        int reflectInstanceHashCode = reflectInstance == null ? -2 : reflectInstance.hashCode();
        int serialiableInstanceHashCode = serialiableInstance == null ? -3 : serialiableInstance.hashCode();
        StringBuilder sb = new StringBuilder("校验 " + title + " 安全：")
                .append("\r\n  ").append("1、instance                  hashCode : ").append(instanceHashCode)
                .append("\r\n  ").append("2、reflect       ")
                .append("结论：").append(instanceHashCode == reflectInstanceHashCode)
                .append("  ").append(" hashCode : ").append(reflectInstanceHashCode)
                .append("\r\n  ").append("3、serializable  ")
                .append("结论：").append(instanceHashCode == serialiableInstanceHashCode)
                .append("  ").append("hashCode : ").append(serialiableInstanceHashCode);
        System.out.println(sb.toString());
    }
}