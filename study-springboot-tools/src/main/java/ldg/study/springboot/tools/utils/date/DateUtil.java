package ldg.study.springboot.tools.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * jdk1.8实现时间工具类
 * <pre>
 *     线程安全
 * </pre>
 *
 * @author： ldg
 * @create date： 2019/3/19
 */
public class DateUtil {
    /*************** jdk1.8  ******************/
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatDate2(LocalDateTime date) {
        return formatter.format(date);
    }

    public static LocalDateTime parse(String dateStr) {
        return LocalDateTime.parse(dateStr, formatter);
    }


    /*************** jdk1.7  ThreadLocal线程安全 ******************/
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static Date parse2(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    public static String format2(Date date) {
        return threadLocal.get().format(date);
    }


    /*************** jdk1.7  同步 线程安全 ******************/
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date parse3(String dateStr) throws ParseException {
        synchronized (sdf) {
            return sdf.parse(dateStr);
        }
    }

    public static String format3(Date date) {
        synchronized (sdf) {
            return sdf.format(date);
        }
    }

    /*************** jdk1.7  实例化 线程安全 ******************/
    public static Date parse4(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dateStr);
    }

    public static String format4(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /*************** jdk1.7  实例化 线程不安全 ******************/
    //原因：申明静态SimpleDateFormat后，calendar就是一个共享变量，可以被多个线程
    //场景：假设线程A执行完calendar.setTime(date)，把时间设置成2019-01-02，这时候被挂起，线程B获得CPU执行权。线程B也执行到了
    //      calendar.setTime(date)，把时间设置为2019-01-03。线程挂起，线程A继续走，calendar还会被继续使用(subFormat方法)，
    //      而这时calendar用的是线程B设置的值了，而这就是引发问题的根源，出现时间不对，线程挂死等等。
    private static final SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date parse5(String dateStr) throws ParseException {
        return sdf5.parse(dateStr);
    }

    public static String format5(Date date) {
        return sdf5.format(date);
    }
}
