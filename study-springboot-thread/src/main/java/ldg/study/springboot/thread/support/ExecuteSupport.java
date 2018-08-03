package ldg.study.springboot.thread.support;

import ldg.study.springboot.thread.service.ExecuteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程管理类
 *
 * @author foursix
 * @since 2017/12/13
 */
@Service
public class ExecuteSupport implements ExecuteService {
    private static Logger logger = LoggerFactory.getLogger(ExecuteSupport.class);
    //CyclicBarrier、Semaphore、ConcurrentHashMap和BlockingQueue
    /*
    线程工具：
        1、CountDownLatch
            定义：
                能够使一个线程等待其他线程完成各自的工作后再执行.

            原理：
                CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。
                每当一个线程完成了自己的任务后，计数器的值就会减1。
                当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。

    线程池：
        1、newFixedThreadPool
            定义：固定大小的线程池（一个线程池最多可同时执行N个线程）

        2、newSingleThreadExecutor
            定义：单线程池，相当于FixedThreadPool(1)

        3、newCachedThreadPool
            定义：可变大小线程池，按照任务数来分配线程
    */

    /**
     * 固定大小线程池
     */
    @Override
    public void newFixedThreadPool() {
        //线程池：一个线程池只允许3个线程同时执行
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        final int count = 10; //并发次数
        //设置累计1000个线程数,只允许设置一次(同步等待结果,不加时为异步处理)
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 1; i <= count; i++) {
            final int j = i;
            System.out.println("[线程  初始化]：  begin " + i);
            //初始化总线程数
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("[线程   执行业务]：  " + j + " " + System.currentTimeMillis());
                    } catch (Exception ex) {

                    }
                    //一个线程执行完成后，线程总数减1
                    countDownLatch.countDown();
                }
            });
            System.out.println("[线程  初始化]：  end" + i);
        }
        try {
            System.out.println("[线程  初始化]：  等待线程执行完成");

            countDownLatch.await();//当累计线程数据大于0时，阻塞；     当累计线程数为0时，释放

            System.out.println("[线程  线程执行完成]：  ");

            executorService.shutdown();//关闭线程池

            System.out.println("[线程  关闭线程池]：  ");
        } catch (Exception e) {
            logger.error("被打断", e);
        }
        logger.info("执行完成了。。。");
    }
}
