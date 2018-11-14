package ldg.study.springboot.thread.support.volatiletest;

import java.util.concurrent.CountDownLatch;

public class VolatileTest2 {
    private static CountDownLatch latch = new CountDownLatch(10*1000);
    public  int inc = 0;
    
    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileTest2 test = new VolatileTest2();
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
