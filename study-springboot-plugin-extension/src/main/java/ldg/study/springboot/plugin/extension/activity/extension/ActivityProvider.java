package ldg.study.springboot.plugin.extension.activity.extension;

import ldg.study.springboot.plugin.extension.activity.extension.ActivityParameter;

/**
 * 活动扩展对外提供的业务接口
 *
 * @author foursix
 * @since 2017/10/16
 */
public interface ActivityProvider {
    /**
     * 获取某一活动对应的插件工厂当中的参数
     *
     * @param type 活动类型
     * @return 活动对应的参数
     */
    ActivityParameter getActivityParameter(int type);
}
