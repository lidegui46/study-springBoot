package ldg.study.springboot.plugin.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 扩展 上下文
 * <p>
 * 目的：应用起动时扫描扩展点和扩展分类，并把扩展点归类到扩展分类中
 * </p>
 *
 * @author foursix
 * @since 2017/10/13
 */
@Component
public class ExtensionContext implements ApplicationContextAware {
    /**
     * 扩展基础Map
     */
    private final List<Extension> extensions = new ArrayList<Extension>();
    /**
     * 扩展定义Map
     */
    private final List<ExtensionAware> extensionAwares = new ArrayList<ExtensionAware>();

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setExtension(applicationContext);
        setExtensionProviderAware(applicationContext);
        for (ExtensionAware extensionAware : extensionAwares) {
            extensions.forEach(extensionAware::addExtension);
        }
    }

    /**
     * 增加扩展基础
     *
     * @param applicationContext 应用上下文
     */
    private void setExtension(ApplicationContext applicationContext) {
        Map<String, Extension> extensionMap = applicationContext.getBeansOfType(Extension.class);
        if (!CollectionUtils.isEmpty(extensionMap)) {
            extensions.addAll(extensionMap.values());
        }
    }

    /**
     * 增加扩展定义
     *
     * @param applicationContext 应用上下文
     */
    private void setExtensionProviderAware(ApplicationContext applicationContext) {
        Map<String, ExtensionAware> extensionProviderMap = applicationContext.getBeansOfType(ExtensionAware.class);
        if (!CollectionUtils.isEmpty(extensionProviderMap)) {
            extensionAwares.addAll(extensionProviderMap.values());
        }
    }
}
