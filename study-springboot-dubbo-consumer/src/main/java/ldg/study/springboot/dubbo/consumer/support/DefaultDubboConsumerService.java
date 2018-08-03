package ldg.study.springboot.dubbo.consumer.support;

import ldg.study.springboot.dubbo.consumer.service.DubboConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Dubbo服务-实现
 *
 * @author foursix
 * @since 2017/9/19
 */
@Service
public class DefaultDubboConsumerService implements DubboConsumerService {
    /*@Autowired
    private DubboProviderService dubboProviderService;*/

    /**
     * 获取Dubbo服务的值
     *
     * @return Dubbo服务的值
     */
    public int getUserId() {
       return 0; //return dubboProviderService.getUser(168);
    }
}
