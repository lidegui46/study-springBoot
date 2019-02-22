package ldg.study.springboot.designPattern.proxy.staticProxy.composeProxy;

import ldg.study.springboot.designPattern.proxy.staticProxy.Car;

/**
 * @author： ldg
 * @create date： 2019/2/22
 */
public class BigCar {
    private Car car;

    public BigCar(Car car) {
        this.car = car;
    }

    public void move() {
        System.out.println("大汽车开走了");
        this.car.move();
        System.out.println("大汽车停下来·了");
    }

    public static void main(String[] args) {
        Car car = new Car();
        BigCar bigCar = new BigCar(car);
        bigCar.move();
    }
}
