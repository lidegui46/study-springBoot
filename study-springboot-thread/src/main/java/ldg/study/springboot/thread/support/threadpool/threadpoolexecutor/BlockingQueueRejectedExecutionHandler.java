package ldg.study.springboot.thread.support.threadpool.threadpoolexecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义拒绝策略处理器 - 重新加入线程池队列
 *
 * @author： ldg
 * @create date： 2019/3/7
 */
public class BlockingQueueRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // 任务被拒绝后，重新加入线程池
        try {
            //重新提交任务
            //executor.execute(r);

            //任务添加到队列
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            System.out.println("rejected executor handler：reput queue error");
            e.printStackTrace();
        }
    }
}