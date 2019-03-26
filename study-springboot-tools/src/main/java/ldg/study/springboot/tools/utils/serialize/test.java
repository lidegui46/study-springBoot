package ldg.study.springboot.tools.utils.serialize;

import java.io.Serializable;

/**
 * @author： ldg
 * @create date： 2019/3/25
 */
public class test implements Serializable {
    private static final long serialVersionUID = 1L;

    private String a;

    /**
     * transient 不被序列化  ，反序列化时，transient变量被设为初始值，如int = 0,对象  = null
     */
    private transient Integer b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
