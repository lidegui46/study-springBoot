package ldg.study.springboot.annotation.service;

import java.lang.annotation.*;

/**
 * 人员登录权限
 * <p>
 * 此注解用于人员权限的设置，用于对类、方法进行权限验证（同一类中有些方法不需要登录访问，有些方法需要登录访问）；
 * 案例场景：
 * </p>
 *
 * @author foursix
 * @since 2017/11/17
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PersonLoginRole {
    /**
     * 角色
     */
    public enum Role {
        None, Manage, Terminal, Seller, Tourist
    }

    /**
     * 描述,默认为空字符串
     */
    String description() default "";

    /**
     * 权限角色，默认为：匿名访问
     *
     * @return
     */
    Role[] role();
}
