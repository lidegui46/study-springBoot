package ldg.study.springboot.event.source;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 指定顺序监听事件源，收集各类活动产生的订单总数
 *
 * @author foursix
 * @since 2017/10/12
 */
public class ActivitySortOrderEventSource implements ActivityOrderEventSource {

    /**
     * 每类活动的订单数量，此属性通过监听器设置
     */
    private Map</* activityType */Integer,/* orderNum */Integer> activityOrderMap
            = new LinkedHashMap<Integer, Integer>();

    public ActivitySortOrderEventSource() {
        super();
        //do nothing
    }

    /***
     * 监听执行完后，设置值
     * @param activityTypeCode 活动类型
     * @param orderNum 活动订单数量
     */
    public void addActivityOrderByListener(Integer activityTypeCode, Integer orderNum) {
        if (!activityOrderMap.containsKey(activityTypeCode)) {
            activityOrderMap.put(activityTypeCode, orderNum);
        }
    }

    public Map</* activityType */Integer,/* orderNum */Integer> getOrderNumMap() {
        return activityOrderMap;
    }
}
