动态代理

    JDK动态代理
    CGLib动态代理

1、JDK动态代理
   
    只能代理实现了接口的类
    
2、CGLib动态代理

    针对类来实现动态代理
    对指定目标类产生一个子类，通过方法拦截技术拦截所有父类方法的调用 
    不能代理final类，或代理被final,private修饰的方法   