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
     * <pre>
     *      添加任务规则：任务先添加到corePoolSize，不足时，再添加到blockingQueue，不足时，再添加到maxPoolSize，不足时，再到rejectedExecutionHandler
     *      销毁线程规则：
     *          allowCoreThreadTimeOut ： 控制是否允许核心线程超时退出
     *              退出对象：核心线程
     *              条件：
     *                  true    ：线程池中的线程空闲时，销毁
     *                  false   ：线程池中的线程空闲时，不处理，保留核心线程
     *          keepAliveTime ： 线程处于空闲状态的时间超过 keepAliveTime 时，就会超时而退出
     *              退出对象：非核心线程（corePoolSize < x < maxPoolSize）
     *              案例：
     *                  1、corePoolSize = 5，poolSize = 8,超出核心大小的线程（8-3）将按照 keepAliveTime 人值判断是否超时退出
     *                  2、corePoolSize = 5，poolSize = 5，核心线程 与 当前线程 相等，则线程不会退出；
     *      空闲线程：线程池中已执行完的线程
     *      提交任务：向线程池中提交待执行的任务，此时没有在线程池中创建线程来执行任务
     *      线   程：线程池中已执行任务的线程（包含：正在执行的线程 和 空闲线程）
     *      最大任务数量：maxPoolSize + blockingQueue
     * </pre>
     *
     * @param corePoolSize  核心线程数量，线程池中线程的基本数量（线程池中可运行的基本线程数据）
     * @param maxPoolSize   线程池中线程的最大数量（线程池中可运行的最大线程数据），最大值：2N + 1 （N为CPU数量）
     * @param keepAliveTime 空闲线程最大存活时间； 空闲线程：线程池中已执行完毕的线程且未执行的时间，超过 keepAliveTime 后，进行销毁
     * @param timeUnit      keepAliveTime  时间单位
     * @param blockingQueue 阻塞队列(区分有界队列和无界队列)，如：new ArrayBlockingQueue<Runnable>(10)    队列中最多只允许有10个任务
     * @param threadFactory 创建线程的工厂
     * @param handler       饱和策略； 触发条件：当提交任务数量 超过 poolSize(poolSize = maxPoolSize) + workQueue 数量之和时
     *                              AbortPolicy     DiscardPolicy       DiscardOldestPolicy     CallerRunsPolicy    自定义
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


