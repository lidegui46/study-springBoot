package ldg.study.springboot.annotation.support;

import ldg.study.springboot.annotation.service.CustomRequestParam;
import ldg.study.springboot.annotation.service.PersonLoginRole;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author foursix
 * @since 2017/11/17
 */
@PersonLoginRole(role = {PersonLoginRole.Role.Tourist}, description = "用户服务 类")
@Service
public class DefaultUserService {

    public void getAnnotationByClass() {
        PersonLoginRole annotation = DefaultUserService.class.getAnnotation(PersonLoginRole.class);
        System.out.println(annotation.description());
        System.out.println(annotation.role());

        getAnnotation(DefaultUserService.class.getDeclaredAnnotations());
    }

    @PersonLoginRole(role = {PersonLoginRole.Role.Tourist}, description = "用户服务 方法")
    public void getAnnotationByMethod(@CustomRequestParam(key = "userId") String userId) {
        Method[] declaredMethods = DefaultUserService.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            //获取方法注解
            getAnnotation(declaredMethod.getDeclaredAnnotations());

            //获取方法注解参数
            getAnnotationByParamters(declaredMethod.getParameterAnnotations());
        }
    }

    /**
     * 获取注解信息
     *
     * @param annotations 注解数组
     */
    private void getAnnotation(Annotation[] annotations) {
        if (annotations != null && annotations.length > 0) {
            for (Annotation declaredAnnotation : annotations) {
                if (declaredAnnotation instanceof PersonLoginRole) {
                    PersonLoginRole personLoginRole = (PersonLoginRole) declaredAnnotation;
                    System.out.println(personLoginRole.description());
                    System.out.println(personLoginRole.role());
                } else if (declaredAnnotation instanceof Service) {
                    System.out.println("this is service");
                }
            }
        }
    }

    private void getAnnotationByParamters(Annotation[][] annotations) {
        for (Annotation[] annotationArr : annotations) {
            if (annotationArr != null) {
                for (Annotation annotation : annotationArr) {
                    if (annotation instanceof CustomRequestParam) {
                        CustomRequestParam customRequestParam = (CustomRequestParam) annotation;
                        System.out.println(customRequestParam.key());
                    }
                }
            }
        }
    }
}
