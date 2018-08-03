package ldg.study.springboot.plugin.extension.web.api;

import ldg.study.springboot.plugin.extension.activity.enums.Activity;
import ldg.study.springboot.plugin.extension.activity.extension.ActivityParameter;
import ldg.study.springboot.plugin.extension.activity.extension.ActivityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 扩展 控制器
 *
 * @author foursix
 * @since 2017/10/16
 */
@RestController
@RequestMapping("/extension")
public class ExtensionController {
    @Autowired
    private ActivityProvider activityProvider;

    /**
     * 扩展控制器
     * 访问地址：http://localhost:8082/extension/index
     * @return
     */
    @RequestMapping(value = "/index")
    public String Index() {
        ActivityParameter activityParameter = activityProvider.getActivityParameter(Activity.Type.Seckill.getCode());
        if (!ObjectUtils.isEmpty(activityParameter)) {
            return activityParameter.getName() + " " + activityParameter.getRuleValidator().validate(null);
        }
        return "no";
    }
}
