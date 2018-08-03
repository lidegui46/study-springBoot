package ldg.study.springboot.tools.web.support;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 支持Jsonp
 *
 * @author foursix
 * @since 2017-09-12
 */
@RestControllerAdvice("ldg.study.springboot.tools.web.api")
public class ToolsJsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public ToolsJsonpAdvice() {
        super("callback");
    }
}