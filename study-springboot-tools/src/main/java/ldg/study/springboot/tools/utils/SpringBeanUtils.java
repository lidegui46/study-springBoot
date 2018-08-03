package ldg.study.springboot.tools.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * BeanUtils
 *
 * @author foursix
 * @since 2017/11/23
 */
public final class SpringBeanUtils {
    /**
     * 获取web容器上下文
     */
    private static WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

    /**
     * 违背了 Spring 依赖注入思想
     *
     * @param beanId
     * @return
     */
    public static Object getBeanByName(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)) {
            throw new Exception("beanId is null");
        }
        return wac.getBean(beanId);

    }

    /**
     * 违背spring的ioc解耦思想。
     */
    public static <T> T getBeanByType(Class clazz) {
        if (clazz == null) {
            return null;
        }
        return (T) wac.getBean(clazz);
    }
}
