package ldg.study.springboot.plugin.factory;

/**
 * 扩展提供者
 * <p>
 * 用于对不同的扩展进行分类，如：活动扩展、文章扩展分类
 * </p>
 *
 * @author foursix
 * @since 2017/10/13
 */
public interface ExtensionAware {
    /**
     * 增加扩展点
     *
     * @param extension 扩展点
     */
    void addExtension(Extension extension);
}
