package ldg.study.springboot.thread.web.api;

import ldg.study.springboot.thread.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 扩展 控制器
 *
 * @author foursix
 * @since 2017/10/16
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {
    @Autowired
    private ThreadService threadService;

    /**
     * 消费
     * 访问地址：http://localhost:8082/thread/produce
     * @return
     */
    @RequestMapping(value = "/produce")
    public String produce() {
        threadService.produce();
        return "ok";
    }

    /**
     * 消费
     * 访问地址：http://localhost:8082/thread/consume
     * @return
     */
    @RequestMapping(value = "/consume")
    public String consume() {
        threadService.consume();
        return "ok";
    }
}
