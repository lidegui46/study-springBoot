package ldg.study.springboot.queue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author： ldg
 * @create date： 2019/3/21
 */
public class test {
    public static void main(String[] args) {
        ArrayBlockingQueue as = new ArrayBlockingQueue(100, true);

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        String a = new String();
        Class<? extends String> aClass = a.getClass();
        try {
            String s = aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Constructor<? extends String> declaredConstructor = aClass.getDeclaredConstructor(String.class, String.class);
            String s = declaredConstructor.newInstance("", "");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

