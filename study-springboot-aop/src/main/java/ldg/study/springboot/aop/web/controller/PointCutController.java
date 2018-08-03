package ldg.study.springboot.aop.web.controller;

import ldg.study.springboot.aop.service.PointCutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * author：      ldg
 * create date： 2018/4/17
 */
@RequestMapping("/aop/pointCut")
@RestController
public class PointCutController {
    @Autowired
    private PointCutService pointCutService;

    private final static Logger logger = LoggerFactory.getLogger(PointCutController.class);

    @GetMapping("/print")
    public String print(String name) {

        logger.info("------------------------ bussiness message: begin -------------------------");

        logger.info("controller print:" + pointCutService.print(name));

        logger.info("------------------------ bussiness message: end -------------------------");

        return "test";
    }

    @GetMapping("/getUser")
    public Map<String, Object> getUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "a1");
        map.put("b", 2);
        map.put("c", new String[]{
            "c1", "c2"
        });
        return map;
    }
}
