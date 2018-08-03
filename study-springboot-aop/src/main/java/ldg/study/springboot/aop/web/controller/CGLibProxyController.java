package ldg.study.springboot.aop.web.controller;

import ldg.study.springboot.aop.ProxyFactory.CGLibProxyFactory;
import ldg.study.springboot.aop.support.UserSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * author：      ldg
 * create date： 2018/4/17
 */
@RequestMapping("/aop/cglibProxy")
@RestController
public class CGLibProxyController {

    private final static Logger logger = LoggerFactory.getLogger(CGLibProxyController.class);

    @GetMapping("/print")
    public String print(String name) {
        UserSupport proxy = (UserSupport) new CGLibProxyFactory(new UserSupport()).createProxy();
        proxy.get("hello");
        proxy.add("world");

        return "test";
    }
}
