package ldg.study.springboot.thread.support.thread.callableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 获取线程返回值
 */
public class ByFutureThread {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        singleFutureTask();
//        threadPoolExecutorFuture();
        threadPoolExecutorFutureByQueue();
    }

    private static void singleFutureTask() throws InterruptedException, ExecutionException {
        // 另一种Runnable，可以有返回值，可以抛出异常
        Callable<String> onlineShopping = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("线程执行开始了。。。");
                Thread.sleep(5000);
                System.out.println("线程执行结束了。。。");
                return "thread result";
            }
        };

        FutureTask<String> task = new FutureTask<String>(onlineShopping);
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("开启 线程了。。。");

        if (!task.isDone()) {
            System.out.println("线程未执行结束...");
        }

        thread.join();//等待线程结束
        System.out.println("结束返回值：" + task.get());
    }

    private static void threadPoolExecutorFuture() throws ExecutionException, InterruptedException {
        int taskSize = 10;
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            final int a = i;
            Callable callable = new Callable() {
                @Override
                public Object call() throws Exception {
                    return a;
                }
            };
            // 执行任务并获取 Future 对象
            Future f = pool.submit(callable);
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();
        // 获取所有并发任务的运行结果
        for (Future f : list) {
            if (f.isDone()) {
                // 从 Future 对象上获取任务的返回值，并输出到控制台
                System.out.println("res：" + f.get().toString());
            }
        }
    }

    private static void threadPoolExecutorFutureByQueue() throws InterruptedException {
        // 队列
        BlockingQueue<Future<String>> futures = new LinkedBlockingQueue<>();
        // 生产者
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                ExecutorService pool = Executors.newCachedThreadPool();

                for (int i = 0; i < 10; i++) {
                    int index = i;
                    Future<String> submit = pool.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return "task done" + index;
                        }
                    });
                    try {
                        futures.put(submit);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("1: " + futures.size());
            }
        };
        thread1.start();

        thread1.join();

        System.out.println("2: "+futures.size());

        // 消费者
        new Thread() {
            @Override
            public void run() {
                int futurueSize = futures.size();
                int futureCompleteCount = 0;
                while (true) {
                    for (Future<String> future : futures) {
                        if (future.isDone()) {
                            futureCompleteCount++;
                            // 处理业务
                            // .............
                            if (futureCompleteCount == futurueSize) {
                                return;
                            }
                            try {
                                System.out.println(futurueSize + " " + futureCompleteCount);
                                System.out.println(future.get().toString());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                        ;
                    }
                }
            }
        }.start();
    }
}
