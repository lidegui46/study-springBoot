package ldg.study.springboot.thread.support.count;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        //同时可执行的线程数据
        //Semaphore semaphore = new Semaphore(5);
        Semaphore semaphore = new Semaphore(5,true);
        //总任务数
        int workerNum = 8;
        for (int i = 0; i < workerNum; i++) {
            new Worker(i, semaphore).start();
        }
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("任务：" + this.num + " 占用一个线程...");
                Thread.sleep(2000);
                System.out.println("任务：" + this.num + " 释放出线程");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
