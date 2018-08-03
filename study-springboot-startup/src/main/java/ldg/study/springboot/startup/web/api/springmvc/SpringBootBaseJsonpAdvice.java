package ldg.study.springboot.startup.web.api.springmvc;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 支持Jsonp
 *
 * @author foursix
 * @since 2017-09-12
 */
@RestControllerAdvice("ldg.study.springboot.base.web.api")
public class SpringBootBaseJsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public SpringBootBaseJsonpAdvice() {
        super("callback");
    }
}