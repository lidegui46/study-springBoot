package ldg.study.springboot.thread.support.volatiletest;

import java.util.concurrent.CountDownLatch;

public class VolatileSimple {
    private static CountDownLatch latch = new CountDownLatch(10*1000);
    // 添加volatile的修饰之后，可以保证其他线程的修改直接写入内存，
    // 并使当前线程的cpu缓存失效，重新从内存中读取
    private int inc1 = 0;
    private volatile int inc2 = 0;
//    private int inc3 = 0;
//    private volatile int inc4 = 0;

    private void add(){
        inc1 = inc1 + 1;
        inc2 = inc2 + 1;
//        inc3++;
//        inc4++;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileSimple test = new VolatileSimple();
        for(int i=0;i<10;i++){
            new Thread(() -> {
                for(int j=0;j<1000;j++){
                  test.add();
                  latch.countDown();
                }
            }).start();
        }

        latch.await();

        System.out.println(test.inc1);
        System.out.println(test.inc2);
//        System.out.println(test.inc3);
//        System.out.println(test.inc4);
    }
}
