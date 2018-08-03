package ldg.study.springboot.mvc.customAnnotation;

import ldg.study.springboot.mvc.customValidator.NotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * 不为空注解
 * author：      ldg
 * create date： 2018/4/17
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmpty {
    String message() default "not_empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
