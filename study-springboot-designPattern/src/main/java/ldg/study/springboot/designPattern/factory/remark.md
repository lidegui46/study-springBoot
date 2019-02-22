工厂模式

1、概念：

    实例化对象，用工厂方法代替new操作（开放-封闭）
    客户端不必关心对象如何创建，明确了职责
    体现面向对象的原则（面向接口编程）

2、分类

    工厂方法模式
    抽象工厂模式，抽象工厂模式是工厂方法模式的扩展

3、场景

    工厂模式：   
        如果生产厂间只有一个，可考虑通过工厂模式来实现
        一种极端情况的抽象工厂模式（抽象中的一种）；
        用来创建一个产品的等级结构
        只有一个抽象产品类
        
    抽象工厂模式：
        如果生产厂间分别生产“速派”、“速腾”，可考虑把工厂抽象出来
        用来创建多个产品的等级结构
        有多个抽象产品类