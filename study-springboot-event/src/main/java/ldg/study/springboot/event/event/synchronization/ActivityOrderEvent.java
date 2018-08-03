package ldg.study.springboot.event.event.synchronization;

import ldg.study.springboot.event.source.ActivityOrderEventSource;
import org.springframework.context.ApplicationEvent;

/**
 * 活动事件
 *
 * @author foursix
 * @since 2017-08-17
 */
public class ActivityOrderEvent extends ApplicationEvent {

    private ActivityOrderEventSource source;

    public ActivityOrderEvent(ActivityOrderEventSource source) {
        super(source);
        this.source = source;
    }

    @Override
    public ActivityOrderEventSource getSource() {
        return source;
    }
}
