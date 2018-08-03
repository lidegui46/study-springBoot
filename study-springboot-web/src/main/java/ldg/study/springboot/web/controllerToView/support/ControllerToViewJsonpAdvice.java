package ldg.study.springboot.web.controllerToView.support;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 设置Http请求时，支持Jsonp格式
 *
 * @author ldg on 2017/9/7.
 */
@ControllerAdvice("ldg.study.springboot.web.controllerToView.api")
public class ControllerToViewJsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public ControllerToViewJsonpAdvice() {
        super("callback");
    }
}