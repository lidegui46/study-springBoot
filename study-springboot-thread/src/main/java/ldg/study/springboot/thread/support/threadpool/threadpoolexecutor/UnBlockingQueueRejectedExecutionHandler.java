package ldg.study.springboot.thread.support.threadpool.threadpoolexecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义拒绝策略处理器 - 丢弃任务
 *
 * @author： ldg
 * @create date： 2019/3/7
 */
public class UnBlockingQueueRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //记录异常
        //报警处理
        System.out.println("thread pool rejected.....");
    }
}