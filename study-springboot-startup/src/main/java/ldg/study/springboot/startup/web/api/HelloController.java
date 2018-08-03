package ldg.study.springboot.startup.web.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author foursix
 * @since 2017/9/19
 */
@RestController
@RequestMapping(value = "/base/hello")
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    /**
     * 访问地址：http://localhost:8082/base/hello/test
     *
     * @return
     */
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        return "你好!";
    }


}
