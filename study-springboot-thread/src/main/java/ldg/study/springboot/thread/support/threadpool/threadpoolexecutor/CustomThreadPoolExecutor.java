package ldg.study.springboot.thread.support.threadpool.threadpoolexecutor;

import java.util.concurrent.*;

/**
 * 非阻塞线程池
 *
 * @author： ldg
 * @create date： 2019/3/7
 */
public class CustomThreadPoolExecutor {

    /**
     * 线程池
     */
    private ThreadPoolExecutor pool = null;

    /**
     * 初始化自定义线程池
     *
     * @param corePoolSize  可运行的核心线程池大小
     * @param maxPoolSize   线程池的最大数量
     * @param keepAliveTime 空闲线程最大存活时间； 空闲线程：线程池中超过corePoolSize数量的等待执行的线程
     * @param timeUnit      keepAliveTime  时间单位
     * @param blockingQueue 阻塞队列(区分有界队列和无界队列)，如：new ArrayBlockingQueue<Runnable>(10)    队列中最多只允许有10个任务
     * @param threadFactory 创建线程的工厂
     * @param handler       拒绝策略； 触发条件：当提交任务数量 超过 maxPoolSize + workQueue 数量之和时
     */
    public void init(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue
            , ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        pool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, blockingQueue
                , threadFactory, handler);
    }

    /**
     * 销毁线程池
     * <pre>
     *     调用销毁方法时，应注意可能存在未执行完的线程
     *     如果考虑线程要全部执行完，请使用countDownLatch
     * </pre>
     */
    public void destory() {
        if (pool != null) {
            pool.shutdown();
            System.out.println("线程池销毁了=======================================");
        }
    }

    /**
     * 获取线程池
     *
     * @return 线程池
     */
    public ThreadPoolExecutor getPool() {
        return pool;
    }
}


