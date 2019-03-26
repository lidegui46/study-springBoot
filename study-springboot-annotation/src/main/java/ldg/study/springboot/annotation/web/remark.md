参考案例说明：
    https://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651479443&idx=1&sn=f84e3b8e3602c9198f72d03c26d291b6&chksm=bd2531ec8a52b8fa024dd44d5eda5050b619446d01dbe1df83fb0619912c2c91b18bb5f5955c&mpshare=1&scene=23&srcid=1117YqWold5kQnjfSbhashSP#rd



标准注解：

    @Override           重写
    @Deprecated         过时
    @SuppressWarnings   关闭警告


Annotation 元素

    @interface  申明注解，作用于类
    @Target     目标（类，方法（普通，构造），字段（类字段，枚举字段），方法参数，本地变量等）
    @Retention  生命周期（RetentionPociy.Source,Class,Runtime）
    @Documented javadoc文档
    @Inherited  继承（父类添加了注解时，子类也同时拥有此注解）


注解三步骤：

    1、定义注解
        @Target(ElementType.FIELD)
        @Retention(RetentionPolicy.RUNTIME)
        @Documented
        public @interface FruitProvider {
        /**供应商编号*/
        public int id() default -1;
            /*** 供应商名称*/
            public String name() default ""；
            /** * 供应商地址*/
            public String address() default "";
         }
    2、使用注解
        public class Apple {
            @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路")
            private String appleProvider;
            public void setAppleProvider(String appleProvider) {
                this.appleProvider = appleProvider;
            }
            public String getAppleProvider() {
                return appleProvider;
            }
        }
    3、注解处理器
        public class FruitInfoUtil {
            public static void getFruitInfo(Class<?> clazz) {
                 String strFruitProvicer = "供应商信息：";
                 Field[] fields = clazz.getDeclaredFields();//通过反射获取处理注解
                 for (Field field : fields) {
                     if (field.isAnnotationPresent(FruitProvider.class)) {
                         FruitProvider fruitProvider = (FruitProvider) field.getAnnotation(FruitProvider.class);
                        //注解信息的处理地方
                        strFruitProvicer = " 供应商编号：" + fruitProvider.id() + " 供应商名称："
                         + fruitProvider.name() + " 供应商地址："+ fruitProvider.address();
                         System.out.println(strFruitProvicer);
                     }
                 }
            }
        }
        
        public class FruitRun {
             public static void main(String[] args) {
                 FruitInfoUtil.getFruitInfo(Apple.class);
                 /***********输出结果***************/
                 // 供应商编号：1 供应商名称：陕西红富士集团 供应商地址：陕西省西安市延
             }
        }