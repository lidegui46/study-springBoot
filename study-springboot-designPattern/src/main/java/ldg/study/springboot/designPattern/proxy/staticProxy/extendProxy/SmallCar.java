package ldg.study.springboot.designPattern.proxy.staticProxy.extendProxy;

import ldg.study.springboot.designPattern.proxy.staticProxy.Car;

/**
 * 小汽车
 *
 * @author： ldg
 * @create date： 2019/2/22
 */
public class SmallCar extends Car {
    public void move1() {
        System.out.println("小汽车开走了");
        super.move();
        System.out.println("小汽车停下来了");
    }

    public static void main(String[] args) {
        SmallCar smallCar = new SmallCar();
        smallCar.move1();
    }
}
