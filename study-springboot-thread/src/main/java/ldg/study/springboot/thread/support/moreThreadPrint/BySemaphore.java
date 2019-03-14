package ldg.study.springboot.thread.support.moreThreadPrint;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 实现多线程交替打印
 * <pre>
 *      Semaphore
 *      作用：限制可以访问某些资源（物理or逻辑）的线程数目
 *      场景：大家排队去银行办理业务，但是只有两个银行窗口提供服务，来了10个人需要办理业务，所以这10个排队的人员需要依次使用这两个业务窗口来办理业务。
 * </pre>
 *
 * @author： ldg
 * @create date： 2019/3/13
 */
public class BySemaphore {
    public static void main(String[] args) {
        try {
            Semaphore aSemaphore = new Semaphore(30);

            aSemaphore.release();
            aSemaphore.release();

            aSemaphore.acquire();

            aSemaphore.acquire(2);
            aSemaphore.release();
            aSemaphore.release();
        } catch (Exception ex) {

        }


        /*// 以A开始的信号量,初始信号量数量为1
        Semaphore aSemaphore = new Semaphore(1);
        // B、C信号量,A完成后开始,初始信号数量为0
        Semaphore bSemaphore = new Semaphore(0);
        Semaphore cSemaphore = new Semaphore(0);

        int printNum = 10;
        SemaphorePrinter aSemaphorePrinter = new SemaphorePrinter("A", aSemaphore, bSemaphore, printNum);
        SemaphorePrinter bSemaphorePrinter = new SemaphorePrinter("B", bSemaphore, cSemaphore, printNum);
        SemaphorePrinter cSemaphorePrinter = new SemaphorePrinter("C", cSemaphore, aSemaphore, printNum);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 30
                , TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(3));
        threadPoolExecutor.execute(aSemaphorePrinter);
        threadPoolExecutor.execute(bSemaphorePrinter);
        threadPoolExecutor.execute(cSemaphorePrinter);
        threadPoolExecutor.shutdown();*/

        /*new Thread(aSemaphorePrinter).start();
        new Thread(bSemaphorePrinter).start();
        new Thread(cSemaphorePrinter).start();*/
    }


    public static class SemaphorePrinter implements Runnable {

        private final String name;
        private final Semaphore selfSemaphore;
        private final Semaphore nextSemapore;
        private final int printNum;

        public SemaphorePrinter(String name, Semaphore selfSemaphore, Semaphore nextSemapore, int printNum) {
            this.name = name;
            this.selfSemaphore = selfSemaphore;
            this.nextSemapore = nextSemapore;
            this.printNum = printNum;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < printNum; i++) {
                    selfSemaphore.acquire();// A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                    if ("C".equals(name)) {
                        System.out.println(name);
                    } else {
                        System.out.print(name);
                    }
                    nextSemapore.release();// B释放信号，B信号量加1（初始为0），此时可以获取B信号量
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
