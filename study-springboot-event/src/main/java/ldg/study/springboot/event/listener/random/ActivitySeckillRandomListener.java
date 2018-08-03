package ldg.study.springboot.event.listener.random;

import ldg.study.springboot.event.enums.Activity;
import ldg.study.springboot.event.event.synchronization.ActivityOrderEvent;
import ldg.study.springboot.event.source.ActivityOrderEventSource;
import ldg.study.springboot.event.source.ActivityRandomOrderEventSource;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 【此监听是无序的】秒杀活动监听器
 *
 * @author foursix
 * @since 2017-08-18
 */
@Component
public class ActivitySeckillRandomListener implements ApplicationListener<ActivityOrderEvent> {
    /**
     * 秒杀活动类型
     */
    private static final int activityType = Activity.Type.Seckill.getCode();

    public void onApplicationEvent(ActivityOrderEvent event) {
        ActivityOrderEventSource eventSource = event.getSource();
        if (eventSource instanceof ActivityRandomOrderEventSource) {
            ActivityRandomOrderEventSource randomEventSource = (ActivityRandomOrderEventSource) eventSource;
            if (!ObjectUtils.isEmpty(randomEventSource)) {
                randomEventSource.addActivityOrderByListener(activityType, 22);
            }
        }
    }
}
