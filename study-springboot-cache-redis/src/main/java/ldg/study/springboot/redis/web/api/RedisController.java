package ldg.study.springboot.redis.web.api;

import ldg.study.springboot.redis.Common.Jedis1Visitor;
import ldg.study.springboot.redis.Common.Jedis2Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis 控制器
 *
 * @author foursix
 * @since 2017/9/20
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    /**
     * 读取公共Cache服务
     */
    @Autowired
    private Jedis1Visitor jedis1Visitor;

    /**
     * 读取默认Cache服务
     */
    @Autowired
    private Jedis2Visitor jedis2Visitor;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Index() {

        jedis1Visitor.setHash("redis", "feild1", "value");

        jedis2Visitor.setHash("redis", "feild2", "value");

        return "";
    }
}
