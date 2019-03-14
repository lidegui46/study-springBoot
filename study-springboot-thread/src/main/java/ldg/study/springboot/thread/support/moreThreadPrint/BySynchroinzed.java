package ldg.study.springboot.thread.support.moreThreadPrint;

/**
 * Synchroinzed  实现多线程交替打印
 * <pre>
 *     思路：顺序执行考虑上一个线程和下一个线程之间的关系
 *          上一个线程对象锁wait，当前线程对象锁notify，循环时行成一个闭环就可循环打印
 * </pre>
 *
 * @author： ldg
 * @create date： 2019/3/13
 */
public class BySynchroinzed {

    public static void main(String[] args) throws Exception {
        int printNum = 10;
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter pa = new ThreadPrinter("A", c, a, printNum);
        ThreadPrinter pb = new ThreadPrinter("B", a, b, printNum);
        ThreadPrinter pc = new ThreadPrinter("C", b, c, printNum);

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
        //上个线程对象
        private Object prev;
        //当前线程对象
        private Object self;

        private ThreadPrinter(String name, Object prev, Object self, int printNum) {
            this.name = name;
            this.prev = prev;
            this.self = self;
            this.printNum = printNum;
        }

        @Override
        public void run() {
            int count = printNum;
            while (count > 0) {// 多线程并发，不能用if，必须使用whil循环
                synchronized (prev) { // 先获取 prev 锁
                    synchronized (self) {// 再获取 self 锁
                        System.out.print(name);// 打印
                        count--;

                        self.notifyAll();// 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                    }
                    // 此时执行完self的同步块，这时self锁才释放。
                    try {
                        if (count == 0) {// 如果count==0,表示这是最后一次打印操作，通过notifyAll操作释放对象锁。
                            prev.notifyAll();
                        } else {
                            prev.wait(); // 立即释放 prev锁，当前线程休眠，等待唤醒
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
