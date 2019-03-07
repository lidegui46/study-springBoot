package ldg.study.springboot.thread.support.threadpool.threadpoolexecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂
 *
 * @author： ldg
 * @create date： 2019/3/7
 */
public class CustomThreadFactory implements ThreadFactory {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String threadName = CustomThreadPoolExecutor.class.getName() + count.addAndGet(1);
        System.out.println(threadName);
        thread.setName(threadName);
        return thread;
    }
}
