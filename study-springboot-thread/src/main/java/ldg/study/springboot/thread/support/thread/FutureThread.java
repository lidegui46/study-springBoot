package ldg.study.springboot.thread.support.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 获取线程返回值
 */
public class FutureThread {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
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

        if(!task.isDone()){
            System.out.println("线程未执行结束...");
        }

        thread.join();//等待线程结束
        System.out.println("结束返回值："+task.get());
    }
}
