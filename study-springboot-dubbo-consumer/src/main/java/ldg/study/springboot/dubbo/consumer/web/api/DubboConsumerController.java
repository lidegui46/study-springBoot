package ldg.study.springboot.dubbo.consumer.web.api;

import ldg.study.springboot.dubbo.consumer.service.DubboConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author foursix
 * @since 2017/9/19
 */
@RestController
@RequestMapping(value = "/dubbo/consumer")
public class DubboConsumerController {
    @Autowired
    private DubboConsumerService dubboConsumerService;

    /**
     * 获取dubbo消费的值
     *
     * @return
     */
    @RequestMapping(value = "/value", method = RequestMethod.GET)
    public int getDubboValue() {
        return dubboConsumerService.getUserId();
    }
}
