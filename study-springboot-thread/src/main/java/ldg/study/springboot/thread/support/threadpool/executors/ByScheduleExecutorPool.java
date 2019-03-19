package ldg.study.springboot.thread.support.threadpool.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 任务调试线程池
 *
 * @author： ldg
 * @create date： 2019/3/14
 */
public class ByScheduleExecutorPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟三秒");
            }
        }, 3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟 1 秒后每三秒执行一次");
            }
        }, 1, 3, TimeUnit.SECONDS);

        long l = System.currentTimeMillis();
        scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("B: " + (System.currentTimeMillis() - l));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("延迟 1 秒后执行一次，每待任务执行完成后，间隔3秒再执行");
            }
        }, 1, 3, TimeUnit.SECONDS);
        System.out.println("A: " + (System.currentTimeMillis() - l));
    }
}
