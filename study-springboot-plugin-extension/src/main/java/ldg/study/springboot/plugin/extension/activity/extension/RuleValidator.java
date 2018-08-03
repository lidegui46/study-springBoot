package ldg.study.springboot.plugin.extension.activity.extension;

import java.util.Map;

/**
 * 规则校验器
 *
 * @author foursix
 * @since 2017-09-30
 */
public interface RuleValidator {

    /**
     * 校验器
     *
     * @param data 校验数据
     * @return 校验是否成功
     */
    boolean validate(Map<String, Object> data);

}
