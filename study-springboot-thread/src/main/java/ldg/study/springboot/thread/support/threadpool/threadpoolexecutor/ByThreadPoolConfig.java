package ldg.study.springboot.thread.support.threadpool.threadpoolexecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @author： ldg
 * @create date： 2019/4/9
 */
@Configuration
public class ByThreadPoolConfig {
    @Bean("consumerQueueThreadPool")
    public ExecutorService buildConsumerQueueThreadPool() {
        ExecutorService pool = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                //ToDO
                return null;
            }
        }, new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }
}

class Consumer{
    @Resource(name = "consumerQueueThreadPool")
    private ExecutorService consumerQueueThreadPool;

    public void execute() {

        //消费队列
        for (int i = 0; i < 5; i++) {
            //consumerQueueThreadPool.execute(new ConsumerQueueThread());
        }

    }
}
