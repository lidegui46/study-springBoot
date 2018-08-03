package ldg.study.springboot.aop.support;

/**
 * author：      ldg
 * create date： 2018/4/17
 */
public class UserSupport {
    public void add(Object o) {
        System.out.println("UserDAO -> Add: " + o.toString());
    }

    public void get(Object o) {
        System.out.println("UserDAO -> Get: " + o.toString());
    }
}
