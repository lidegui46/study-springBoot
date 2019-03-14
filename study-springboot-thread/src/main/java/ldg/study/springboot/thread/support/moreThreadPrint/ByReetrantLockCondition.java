package ldg.study.springboot.thread.support.moreThreadPrint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReetrantLockCondition  实现多线程交替打印
 *
 * ********************** 暂未实现 **********************
 *
 * <pre>
 *     思路：顺序执行考虑上一个线程和下一个线程之间的关系
 *          上一个线程对象锁wait，当前线程对象锁notify，循环时行成一个闭环就可循环打印
 * </pre>
 *
 * @author： ldg
 * @create date： 2019/3/13
 */
public class ByReetrantLockCondition {

    private static Lock lock = new ReentrantLock();
    private static Condition threadA = lock.newCondition();
    private static Condition threadB = lock.newCondition();
    private static Condition threadC = lock.newCondition();

    private static final Integer printNum = 10;
    private static final Integer count = 0;

    public static void main(String[] args) throws Exception {
        ThreadPrinter pa = new ThreadPrinter("A", threadC, threadA, printNum);
        ThreadPrinter pb = new ThreadPrinter("B", threadA, threadB, printNum);
        ThreadPrinter pc = new ThreadPrinter("C", threadB, threadC, printNum);

        // 保证初始ABC的启动顺序
        new Thread(pa).start();
        Thread.sleep(10);
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }

    public static class ThreadPrinter implements Runnable {
        private String name;
        private int printNum;
        //当前线程对象
        private Condition selfThread;
        //上个线程对象
        private Condition nextThread;

        private ThreadPrinter(String name, Condition prevThread, Condition selfThread, int printNum) {
            this.name = name;
            this.nextThread = prevThread;
            this.selfThread = selfThread;
            this.printNum = printNum;
        }

        @Override
        public void run() {
            try {
                lock.lock();

                while (count > 0) {
                    System.out.print(name);

                    //通知下一个线程
                    nextThread.signal();

                    //当前线程处理等待状态
                    selfThread.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}