package ldg.study.springboot.thread.support.threadpool.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author： ldg
 * @create date： 2019/3/7
 */
public class ThreadPoolExecutorMain {

    public static void main(String[] args) {
        //unBlockingQueueThreadPoolExecutor();
        blockingQueueThreadPoolExecutor();
    }

    /**
     * 拒绝策略 - 丢弃
     */
    private static void unBlockingQueueThreadPoolExecutor() {
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor();
        //初始化线程池
        executor.init(3, 10, 30, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5)
                , new CustomThreadFactory(), new UnBlockingQueueRejectedExecutionHandler());
        submitTask(executor.getPool());
        //销毁线程
        executor.destory();
    }

    /**
     * 拒绝策略 - 重入线程池队列
     */
    private static void blockingQueueThreadPoolExecutor() {
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor();
        //初始化线程池
        executor.init(1, 3, 30, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5)
                , new CustomThreadFactory(), new BlockingQueueRejectedExecutionHandler());
        submitTask(executor.getPool());
        //销毁线程
        executor.destory();
    }

    private static void submitTask(ThreadPoolExecutor pool) {
        printTask(pool,"提交任务前");
        //线程
        for (int i = 0; i < 30; i++) {
            final int a = i;
            System.out.printf("提交第 %s 个任务 \r\n", i).println("");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.printf("第 %s 个任务 Running \r\n", a).println("");
                }
            });
        }

        printTask(pool,"提交任务后");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printTask(pool,"线程池执行完");
    }

    private static void printTask(ThreadPoolExecutor pool, String title) {
        StringBuilder sb1 = new StringBuilder(title)
                .append(":")
                .append("\n   ").append("任务数量：").append(pool.getTaskCount())
                .append("\n   ").append("激活数量：").append(pool.getActiveCount())
                .append("\n   ").append("队列数量：").append(pool.getQueue().size())
                .append("\n   ").append("完成数量：").append(pool.getCompletedTaskCount());
        System.out.println(sb1.toString());
    }
}
