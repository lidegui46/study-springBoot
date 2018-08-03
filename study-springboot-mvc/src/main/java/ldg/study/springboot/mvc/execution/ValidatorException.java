package ldg.study.springboot.mvc.execution;

/**
 * 验证异常
 * author：      ldg
 * create date： 2018/4/17
 */
public class ValidatorException extends CustomException {
    public ValidatorException(Integer code, String message, Object... params) {
        super(code, message, params);
    }
}
