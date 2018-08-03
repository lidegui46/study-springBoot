package ldg.study.springboot.mvc.web.api.support;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 支持Jsonp
 *
 * @author foursix
 * @since 2017-09-12
 */
@RestControllerAdvice("ldg.study.springboot.mvc.web.api")
public class SpringBootMvcJsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public SpringBootMvcJsonpAdvice() {
        super("callback");
    }
}
