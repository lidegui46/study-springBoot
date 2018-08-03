package ldg.study.springboot.plugin.extension.activity.extensions.seckill;

import ldg.study.springboot.plugin.extension.activity.enums.Activity;
import ldg.study.springboot.plugin.extension.activity.extension.ActivityParameter;
import ldg.study.springboot.plugin.extension.activity.extension.RuleValidator;
import ldg.study.springboot.plugin.factory.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 秒杀 参数
 *
 * @author foursix
 * @since 2017/10/16
 */
@Component("seckillParameter")
public class SeckillParameter implements ActivityParameter {
    @Autowired
    private SeckillRuleValidator seckillRuleValidator;

    /**
     * 活动类型
     *
     * @return
     */
    public int getType() {
        return Activity.Type.Seckill.getCode();
    }

    /**
     * 活动名称
     *
     * @return
     */
    public String getName() {
        return Activity.Type.of(Activity.Type.Seckill.getCode()).getDesc();
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
     * 获取秒杀规则校验器
     *
     * @return 秒杀规则校验器
     */
    public RuleValidator getRuleValidator() {
        return seckillRuleValidator;
    }
}
