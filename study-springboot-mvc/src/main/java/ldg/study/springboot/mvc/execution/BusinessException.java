package ldg.study.springboot.mvc.execution;

/**
 * 业务异常
 * author：      ldg
 * create date： 2018/4/17
 */
public class BusinessException extends CustomException {

    public BusinessException(Integer code, String message, Object... params) {
        super(code, message, params);
    }
}
