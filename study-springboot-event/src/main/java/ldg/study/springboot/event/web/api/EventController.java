package ldg.study.springboot.event.web.api;

import ldg.study.springboot.event.event.synchronization.ActivityOrderEvent;
import ldg.study.springboot.event.source.ActivityRandomOrderEventSource;
import ldg.study.springboot.event.source.ActivitySortOrderEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 事件案例 控制器
 *
 * @author foursix
 * @since 2017/9/19
 */
@RestController
@RequestMapping(value = "/event")
public class EventController implements ApplicationEventPublisherAware {
    /**
     * 事件使用是是规约，业务描述：A业务需要B业务提供数据；
     * 事件可用于业务与业务的解耦
     * 面向过程开发：
     * 按照“A业务 调用 B业务”，A业务强制依赖B业务；
     * <p>
     * 面向对象开发：
     * A业务公布一个事件，B业务监听事件并设置返回值给A业务，实现业务模块与模块之间的耦合（B业务依赖A业务，B业务移除不影响A业务）；
     */

    private final Logger logger = LoggerFactory.getLogger(EventController.class);

    private ApplicationEventPublisher applicationEventPublisher;

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 监听器随机监听事件
     * 访问地址：http://localhost:8082/event/randomListener
     *
     * @return
     */
    @RequestMapping(value = "randomListener", method = RequestMethod.GET)
    public String randomListener() {
        ActivityRandomOrderEventSource eventSource = new ActivityRandomOrderEventSource();
        ActivityOrderEvent event = new ActivityOrderEvent(eventSource);
        applicationEventPublisher.publishEvent(event);

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : eventSource.getOrderNumMap().entrySet()) {
            sb.append("<br>          活动类型：" + entry.getKey() + "  订单数量：" + entry.getValue());
        }
        return "【监听器随机监听事件】：" + sb.toString();
    }

    /**
     * 监听器按顺序监听事件
     * 访问地址：http://localhost:8082/event/sortListener
     *
     * @return
     */
    @RequestMapping(value = "sortListener", method = RequestMethod.GET)
    public String sortListener() {
        ActivitySortOrderEventSource eventSource = new ActivitySortOrderEventSource();
        ActivityOrderEvent event = new ActivityOrderEvent(eventSource);
        applicationEventPublisher.publishEvent(event);

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : eventSource.getOrderNumMap().entrySet()) {
            sb.append("<br>          活动类型：" + entry.getKey() + "  订单数量：" + entry.getValue());
        }
        return "【监听器按顺序监听事件】：" + sb.toString();
    }
}
