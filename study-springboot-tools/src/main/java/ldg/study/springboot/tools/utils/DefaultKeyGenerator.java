package ldg.study.springboot.tools.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Calendar;

/**
 * created on 2017-12-19 11:26
 *
 * @author nextyu
 */
@Component
public class DefaultKeyGenerator {
    public static final long EPOCH;

    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_BITS = 10L;

    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;


    private long workerId = 0;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }

    private long sequence;

    private long lastTime;

    public DefaultKeyGenerator() {

    }

    public DefaultKeyGenerator(long workerId) {
        Assert.isTrue(workerId >= 0L && workerId < WORKER_ID_MAX_VALUE);
        this.workerId = workerId;
    }

    /**
     * Generate key.
     *
     * @return key type is @{@link Long}.
     */
    public synchronized long generateKey() {
        long currentMillis = getCurrentMillis();
        Assert.isTrue(lastTime <= currentMillis, String.format("Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastTime, currentMillis));
        if (lastTime == currentMillis) {
            if (0L == (sequence = ++sequence & SEQUENCE_MASK)) {
                currentMillis = waitUntilNextTime(currentMillis);
            }
        } else {
            sequence = 0;
        }
        lastTime = currentMillis;
        return ((currentMillis - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    private long waitUntilNextTime(final long lastTime) {
        long time = getCurrentMillis();
        while (time <= lastTime) {
            time = getCurrentMillis();
        }
        return time;
    }

    /**
     * Get current millis.
     *
     * @return current millis
     */
    private long getCurrentMillis() {
        return System.currentTimeMillis();
    }
}
