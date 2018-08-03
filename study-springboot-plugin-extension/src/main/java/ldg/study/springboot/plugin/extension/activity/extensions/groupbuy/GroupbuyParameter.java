package ldg.study.springboot.plugin.extension.activity.extensions.groupbuy;

import ldg.study.springboot.plugin.extension.activity.enums.Activity;
import ldg.study.springboot.plugin.extension.activity.extension.ActivityParameter;
import ldg.study.springboot.plugin.extension.activity.extension.RuleValidator;
import ldg.study.springboot.plugin.factory.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 团购 参数
 *
 * @author foursix
 * @since 2017/10/16
 */
@Component("groupbuyParameter")
public class GroupbuyParameter implements ActivityParameter {

    @Autowired
    private GroupBuyRuleValidator groupBuyRuleValidator;

    /**
     * 活动类型
     *
     * @return
     */
    public int getType() {
        return Activity.Type.GroupBuy.getCode();
    }

    /**
     * 活动名称
     *
     * @return
     */
    public String getName() {
        return Activity.Type.of(Activity.Type.GroupBuy.getCode()).getDesc();
    }

    /**
     * 是否为招募规则
     *
     * @return
     */
    public boolean getRecruitActivity() {
        return false;
    }

    /**
     * 获取校验器
     *
     * @return
     */
    public RuleValidator getRuleValidator() {
        return groupBuyRuleValidator;
    }
}
