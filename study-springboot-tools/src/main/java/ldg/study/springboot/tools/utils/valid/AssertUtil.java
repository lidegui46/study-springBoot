package ldg.study.springboot.tools.utils.valid;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author： ldg
 * @create date： 2018/12/26
 */
public class AssertUtil {

    /**
     * 是否为True
     *
     * @param bool    false：throw exception
     * @param message
     * @param args
     */
    public static void isTrue(boolean bool, String message, Object... args) {
        if (!bool) {
            throw new BusinessException(message, args);
        }
    }

    /**
     * 是否为False
     *
     * @param bool    true：throw exception
     * @param message
     * @param args
     */
    public static void isFalse(boolean bool, String message, Object... args) {
        if (bool) {
            throw new BusinessException(message, args);
        }
    }

    /**
     * 是否为空字符串
     *
     * @param str     空：throw exception
     * @param message
     * @param args
     */
    public static void isEmpty(String str, String message, Object... args) {
        if (StringUtils.isEmpty(str)) {
            throw new BusinessException(message, args);
        }
    }

    /**
     * 是否为空字符串
     *
     * @param str     非空：throw exception
     * @param message
     * @param args
     */
    public static void notEmpty(String str, String message, Object... args) {
        if (!StringUtils.isEmpty(str)) {
            throw new BusinessException(message, args);
        }
    }

    /**
     * 有内容
     *
     * @param str     空：throw exception
     * @param message
     * @param args
     */
    public static void hasText(String str, String message, Object... args) {
        if (StringUtils.hasText(str)) {
            throw new BusinessException(message, args);
        }
    }

    /**
     * 是否为Null
     *
     * @param obj
     * @param message
     * @param args
     */
    public static void isNull(Object obj, String message, Object... args) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new BusinessException(message, args);
        }
    }

    /**
     * 是否为空
     *
     * @param obj     非空：throw exception
     * @param message
     * @param args
     */
    public static void notNull(Object obj, String message, Object... args) {
        if (!ObjectUtils.isEmpty(obj)) {
            throw new BusinessException(message, args);
        }
    }

    /**
     * 是否为空
     *
     * @param collection 空：throw exception
     * @param message
     * @param args
     */
    public static void isEmpty(Collection<?> collection, String message, Object... args) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(message, args);
        }
    }

    /**
     * 是否为空
     *
     * @param collection 非空：throw exception
     * @param message
     * @param args
     */
    public static void notEmpty(Collection<?> collection, String message, Object... args) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(message, args);
        }
    }

    /**
     * 是否为空
     *
     * @param map     空：throw exception
     * @param message
     * @param args
     */
    public static void isEmpty(Map<?, ?> map, String message, Object... args) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BusinessException(message, args);
        }
    }

    /**
     * 是否为空
     *
     * @param map     非空：throw exception
     * @param message
     * @param args
     */
    public static void notEmpty(Map<?, ?> map, String message, Object... args) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new BusinessException(message, args);
        }
    }
}
