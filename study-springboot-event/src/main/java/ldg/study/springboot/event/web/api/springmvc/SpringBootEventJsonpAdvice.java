package ldg.study.springboot.event.web.api.springmvc;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 支持Jsonp
 *
 * @author foursix
 * @since 2017-09-12
 */
@RestControllerAdvice("ldg.study.springboot.event.web.api")
public class SpringBootEventJsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public SpringBootEventJsonpAdvice() {
        super("callback");
    }
}