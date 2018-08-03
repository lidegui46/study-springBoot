package ldg.study.springboot.job.quartz.web.support;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 设置Http请求时，支持Jsonp格式
 *
 * @author ldg on 2017/9/7.
 */
@ControllerAdvice("ldg.study.springboot.orm.mybatis.web.api")
public class JobQuartzJsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public JobQuartzJsonpAdvice() {
        super("callback");
    }
}