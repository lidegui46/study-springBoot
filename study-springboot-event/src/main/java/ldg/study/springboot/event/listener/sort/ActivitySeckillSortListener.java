package ldg.study.springboot.event.listener.sort;

import ldg.study.springboot.event.enums.Activity;
import ldg.study.springboot.event.event.synchronization.ActivityOrderEvent;
import ldg.study.springboot.event.source.ActivityOrderEventSource;
import ldg.study.springboot.event.source.ActivitySortOrderEventSource;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 【此监听是无序的】秒杀活动监听器
 *
 * @author foursix
 * @since 2017-08-18
 */
@Component
public class ActivitySeckillSortListener implements SmartApplicationListener {
    /**
     * 秒杀活动类型
     */
    private static final Activity.Type activityType = Activity.Type.Seckill;

    /**
     * 根据事件类型动态监听
     *
     * @param aClass
     * @return
     */
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return true;
        //return aClass == ActivityOrderEvent.class;
    }

    /**
     * 根据事件发布者类型动态监听
     *
     * @param aClass
     * @return
     */
    public boolean supportsSourceType(Class<?> aClass) {
        return true;
        //return aClass == ActivityOrderEventSource.class;
    }

    /**
     * 监听事件
     *
     * @param applicationEvent
     */
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        ActivityOrderEvent activityOrderEvent = (ActivityOrderEvent) applicationEvent;

        ActivityOrderEventSource eventSource = activityOrderEvent.getSource();
        if (eventSource instanceof ActivitySortOrderEventSource) {
            ActivitySortOrderEventSource sortEventSource = (ActivitySortOrderEventSource) eventSource;
            if (!ObjectUtils.isEmpty(sortEventSource)) {
                sortEventSource.addActivityOrderByListener(activityType.getCode(), 33);
            }
        }
    }

    /**
     * 设置事件监听器顺序
     * <p>
     *     优先级越小，则越先被调用
     *     值相同时，再按随机执行
     * </p>
     *
     * @return
     */
    public int getOrder() {
        return 2;
    }
}
