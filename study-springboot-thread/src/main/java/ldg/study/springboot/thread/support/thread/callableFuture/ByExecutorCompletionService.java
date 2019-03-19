package ldg.study.springboot.thread.support.thread.callableFuture;

import java.util.concurrent.*;

/**
 * ExecutorCompletionService
 * jdk 自带线程池结果管理器：ExecutorCompletionService
 * 它将BlockingQueue 和Executor 封装起来。然后使用ExecutorCompletionService.submit()方法提交任
 *
 * @author： ldg
 * @create date： 2019/3/14
 */
public class ByExecutorCompletionService {
    public static void main(String[] args) {
        ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(
                Executors.newCachedThreadPool());


        // 生产者
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    int index = i;
                    service.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return "task done" + index;
                        }
                    });
                }
            }
        }.start();

        // 消费者
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true){
                        Future<String> take = service.take();
                        System.out.println(take.get().toString());
                        // do some thing........
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
