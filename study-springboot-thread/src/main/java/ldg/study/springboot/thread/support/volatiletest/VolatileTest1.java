package ldg.study.springboot.thread.support.volatiletest;

import java.util.concurrent.CountDownLatch;

/**
 * volatile并不能保证原子性
 */
public class VolatileTest1 {
    private static CountDownLatch latch = new CountDownLatch(10*1000);
    private volatile int inc = 0;

    private void increase() {
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileTest1 test = new VolatileTest1();
        for(int i=0;i<10;i++){
            new Thread(() -> {
                for(int j=0;j<1000;j++) {
                    test.increase();
                    latch.countDown();
                }
            }).start();
        }

        latch.await();
        System.out.println(test.inc);
    }
}
