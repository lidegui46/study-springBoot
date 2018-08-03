package ldg.study.springboot.dubbo.provider.support;

import ldg.study.springboot.dubbo.provider.api.service.DubboProviderService;
import org.springframework.stereotype.Service;

/**
 * Dubbo提供者-实现者
 *
 * @author foursix
 * @since 2017/9/19
 */
@Service
public class DefaultDubboProviderService implements DubboProviderService {

    /**
     * 获取用户信息
     *
     * @param userId 用户编号
     * @return 用户信息
     */
    public int getUser(int userId) {
        return 1;
    }
}
