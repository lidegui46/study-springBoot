package ldg.study.springboot.plugin.extension.activity.support;

import ldg.study.springboot.plugin.extension.activity.extension.ActivityParameter;
import ldg.study.springboot.plugin.extension.activity.extension.ActivityProvider;
import ldg.study.springboot.plugin.factory.Extension;
import ldg.study.springboot.plugin.factory.ExtensionAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动扩展
 *
 * @author foursix
 * @since 2017/10/16
 */
@Component
public class DefaultActivityExtensionProvider implements ActivityProvider, ExtensionAware {

    /**
     * 活动扩展map
     */
    private Map<Integer, ActivityParameter> activityExtensionMap = new HashMap<Integer, ActivityParameter>();

    /**
     * 添加活动扩展
     *
     * @param extension 扩展点
     */
    public void addExtension(Extension extension) {
        if (!ObjectUtils.isEmpty(extension)) {
            if (extension instanceof ActivityParameter) {
                ActivityParameter activityExtension = (ActivityParameter) extension;
                activityExtensionMap.put(activityExtension.getType(), activityExtension);
            }
        }
    }

    /**
     * 获取某一活动对应的插件工厂当中的参数
     *
     * @param type 活动类型
     * @return 活动对应的参数
     */
    public ActivityParameter getActivityParameter(int type) {
        return activityExtensionMap.get(type);
    }
}
