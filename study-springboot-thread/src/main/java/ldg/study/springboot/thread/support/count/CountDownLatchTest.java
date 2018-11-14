package ldg.study.springboot.thread.support.count;

import java.util.concurrent.CountDownLatch;

/**
 * 开启N个线程，并等待N个线程全部执行完成
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        int threadCount = 10;
        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {

            new Thread(() -> {

                System.out.println("线程" + Thread.currentThread().getId() + "开始出发");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程" + Thread.currentThread().getId() + "已到达终点");

                // latch 计算，表示“CountDownLatch”执行完一次
                latch.countDown();
            }).start();
        }

        try {
            // 等待 CountDownLatch 所有线程执行完成
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("10个线程已经执行完毕！开始计算排名");
    }
}
