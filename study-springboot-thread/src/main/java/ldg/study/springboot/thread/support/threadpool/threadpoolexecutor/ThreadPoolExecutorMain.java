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
        unBlockingQueueThreadPoolExecutor();
        blockingQueueThreadPoolExecutor();
    }

    /**
     * 拒绝策略 - 丢弃
     */
    private static void unBlockingQueueThreadPoolExecutor() {
        CustomThreadPoolExecutor executor = new CustomThreadPoolExecutor();
        //初始化线程池
        executor.init(10, 30, 30, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10)
                , new CustomThreadFactory(), new UnBlockingQueueRejectedExecutionHandler());
        initThread(executor.getPool());
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
        initThread(executor.getPool());
        //销毁线程
        executor.destory();
    }

    private static void initThread(ThreadPoolExecutor pool) {
        //线程
        for (int i = 0; i < 300; i++) {
            final int a = i;
            System.out.printf("提交第 %s 个任务 \r\n", i);
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("");
                    System.out.printf("第 %s 个任务 Running \r\n", a);
                }
            });
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
