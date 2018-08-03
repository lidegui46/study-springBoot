package ldg.study.springboot.job.scheduler.support;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author foursix
 * @since 2017/11/16
 */
@Service
@EnableScheduling
public class DefaultJobService {
    public final static long ONE_Minute = 1 * 1000;

    /**
     * 固定等待时间，PS：固定N毫秒执行
     * <p>
     * 单位：毫秒
     * </p>
     */
    @Scheduled(fixedDelay = ONE_Minute)
    public void fixedDelayJob() {
        //System.out.println(formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + " >>fixedDelay执行....");
        System.out.println(System.nanoTime() / 1000 / 1000 + " >>fixedDelay执行....");
    }

    /**
     * 固定间隔时间，PS：任务执行完后间隔N毫秒执行
     * <p>
     * 单位：毫秒
     * </p>
     */
    @Scheduled(fixedRate = ONE_Minute)
    public void fixedRateJob() {
        //System.currentTimeMillis();
        //System.nanoTime();
        //System.out.println(formatDate(new Date(), "yyyy-MM-dd HH:mm:ss ms") + " >>fixedRate执行....");
        System.out.println(System.nanoTime() / 1000 / 1000 + " >>fixedRate执行....");
    }

    /**
     * Corn表达式
     * <p>
     * 测试结果：
     * "1 * * * * ?"       不会执行
     * "1/1 * * * * ?"     会执行
     * </p>
     */
    @Scheduled(cron = "1/1 * * * * ?")
    public void cronJob() {
        //System.out.println(formatDate(new Date(), "yyyy-MM-dd HH:mm:ss ms") + " >>cron执行....");
        System.out.println(System.nanoTime() / 1000 / 1000 + " >>cron执行....");
    }

    private String formatDate(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
}
