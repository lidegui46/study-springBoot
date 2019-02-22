package ldg.study.springboot.designPattern.proxy.dynamicProxy.jdkProxy;

import ldg.study.springboot.designPattern.proxy.dynamicProxy.Moveable;

/**
 * 红色车辆
 *
 * @author： ldg
 * @create date： 2019/2/22
 */
public class RedCar implements Moveable {
    @Override
    public String move() {
        System.out.println("红色汽车开走了");
        return "OK";
    }
}
