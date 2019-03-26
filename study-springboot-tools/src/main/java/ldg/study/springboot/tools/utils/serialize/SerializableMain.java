package ldg.study.springboot.tools.utils.serialize;

/**
 * @author： ldg
 * @create date： 2019/3/25
 */
public class SerializableMain {
    public static void main(String[] args) {
        String testFile = "d:/test1.txt";
        /*test test1 = new test();
        test1.setA("a");
        test1.setB(1);
        SerializableUtil.serializeToFile(test1, testFile);*/

        test test11 = (test) SerializableUtil.deserializeByFile(testFile);
        //false   反序列化后，非同一个对象
        //System.out.println(test1 == test11);
    }
}
