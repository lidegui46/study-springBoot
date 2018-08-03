package ldg.study.springboot.mvc.customValidator;

import ldg.study.springboot.mvc.customAnnotation.NotEmpty;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * author：      ldg
 * create date： 2018/4/17
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

    @Override
    public void initialize(NotEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value);
    }
}