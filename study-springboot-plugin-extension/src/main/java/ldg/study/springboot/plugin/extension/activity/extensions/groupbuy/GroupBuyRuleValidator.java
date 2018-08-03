package ldg.study.springboot.plugin.extension.activity.extensions.groupbuy;

import ldg.study.springboot.plugin.extension.activity.extension.RuleValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 团购规则校验器
 *
 * @author foursix
 * @since 2017/10/16
 */
@Component("groupBuyRuleValidator")
public class GroupBuyRuleValidator implements RuleValidator {
    /**
     * 团购校验器
     *
     * @param data 校验数据
     * @return 校验是否成功
     */
    public boolean validate(Map<String, Object> data) {
        System.out.println("团购规则校验");
        return false;
    }
}
