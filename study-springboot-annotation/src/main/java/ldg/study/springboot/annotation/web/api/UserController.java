package ldg.study.springboot.annotation.web.api;

import ldg.study.springboot.annotation.service.PersonLoginRole;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 用户控制器
 * <p>
 * 权限控制原理，通过filter对访问mvc进行拦截，在filter中对访问的权限进行验证
 * </p>
 *
 * @author foursix
 * @since 2017/11/17
 */
@RestController
@RequestMapping(value = "/annotation/user")
public class UserController {
    /**
     * 只有管理员可修改
     *
     * @return
     */
    @PersonLoginRole(role = {PersonLoginRole.Role.Manage})
    @RequestMapping(value = "/editUser")
    public String editUser() {
        return "";
    }

    /**
     * 管理员、商业、终端可 修改自己的名字
     *
     * @return
     */
    @PersonLoginRole(role = {PersonLoginRole.Role.Manage, PersonLoginRole.Role.Seller, PersonLoginRole.Role.Terminal})
    @RequestMapping(value = "/editUserName")
    public String editUserName() {
        return "";
    }

    /**
     * 查看文章,不需要权限
     *
     * @return
     */
    @PersonLoginRole(role = {PersonLoginRole.Role.None})
    @RequestMapping(value = "/getActicle")
    public String getActicle() {
        return "";
    }

    /**
     * 获取注解
     */
    private void getAnnotation() {
        Class userControllerClass = UserController.class;
        for (Method method : userControllerClass.getMethods()) {
            PersonLoginRole annotation = (PersonLoginRole) method.getAnnotation(PersonLoginRole.class);
            if (annotation != null) {
                System.out.println(" Method Name : " + method.getName());
                System.out.println(" description : " + annotation.description());
                System.out.println(" role : " + (annotation.role().length > 0 ? Arrays.toString(annotation.role()) : ""));
            }
        }

    }
}
