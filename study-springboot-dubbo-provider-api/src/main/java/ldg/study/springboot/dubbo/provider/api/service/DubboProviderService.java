package ldg.study.springboot.dubbo.provider.api.service;

/**
 * 对外提供的dubbo服务
 *
 * @author foursix
 * @since 2017/9/19
 */
public interface DubboProviderService {
    /**
     * 获取用户信息
     *
     * @param userId 用户编号
     * @return 用户信息
     */
    int getUser(int userId);
}
