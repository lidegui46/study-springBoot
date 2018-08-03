package ldg.study.springboot.plugin.extension.activity.extension;

import ldg.study.springboot.plugin.factory.Extension;

/**
 * 活动扩展
 *
 * @author foursix
 * @since 2017/10/16
 */
public interface ActivityParameter extends Extension {
    /**
     * 活动类型
     *
     * @return
     */
    int getType();

    /**
     * 活动名称
     *
     * @return
     */
    String getName();

    /**
     * 是否为招募规则
     *
     * @return
     */
    boolean getRecruitActivity();

    /**
     * 获取校验证器
     *
     * @return 校验器
     */
    RuleValidator getRuleValidator();
}
