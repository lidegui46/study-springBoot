package ldg.study.springboot.designPattern.factory.factory;

/**
 * 速腾 汽车
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class SuTenCar extends Car {
    public SuTenCar() {
        System.out.println("速腾车上场");
    }

    @Override
    public void drive() {
        System.out.println("            开车了");
    }
}
