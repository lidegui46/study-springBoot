package ldg.study.springboot.annotation.service;

import java.lang.annotation.*;

/**
 * 自定义请求参数
 * <p>
 * ：
 * </p>
 *
 * @author foursix
 * @since 2017/11/17
 */
@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CustomRequestParam {

    /**
     * 参数 key
     */
    String key() default "";

    /**
     * 参数 value
     */
    String value() default "";

    /**
     * 是否必要参数
     *
     * @return
     */
    boolean require() default false;
}
