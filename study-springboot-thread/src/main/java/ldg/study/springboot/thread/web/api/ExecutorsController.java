package ldg.study.springboot.thread.web.api;

import ldg.study.springboot.thread.service.ExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理类
 *
 * @author foursix
 * @since 2017/12/13
 */
@RestController
@RequestMapping("/executors")
public class ExecutorsController {
    @Autowired
    private ExecuteService executorsService;

    /**
     * 消费
     * 访问地址：http://localhost:8082/executors/newFixedThreadPool
     *
     * @return
     */
    @RequestMapping(value = "/newFixedThreadPool")
    public String newFixedThreadPool() {
        executorsService.newFixedThreadPool();
        return "ok";
    }
}