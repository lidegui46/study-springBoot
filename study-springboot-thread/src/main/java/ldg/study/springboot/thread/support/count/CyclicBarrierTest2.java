package ldg.study.springboot.thread.support.count;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 */
public class CyclicBarrierTest2 {
    public static void main(String[] args) {
        int cyclicNum = 4;
        CyclicBarrier barrier = new CyclicBarrier(cyclicNum
                ,/*最后一个线程*/ () -> System.out.println("Current Thread Name : " + Thread.currentThread().getName()));

        for (int i = 0; i < cyclicNum; i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("Thread Name : " + threadName + " ,正在写入数据...");
            try {
                Thread.sleep(5000);
                System.out.println("Thread Name : " + threadName + " ,写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
