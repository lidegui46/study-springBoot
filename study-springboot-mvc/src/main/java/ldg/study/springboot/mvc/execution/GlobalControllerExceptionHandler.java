package ldg.study.springboot.mvc.execution;

import ldg.study.springboot.mvc.dto.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * Controller异常拦截
 * author：      ldg
 * create date： 2018/4/17
 * 参考地址：http://www.importnew.com/21589.html#comment-650356
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse constraintBusinessException(BusinessException ex) {
        log.error("Business exception, errorCode: {}, errorDesc: {}", ex.getErrorCode(), ex.getMessage());
        return new ApiErrorResponse(ex.getErrorCode(), ex.getMessage(), ex);
    }

    @ExceptionHandler(value = {SystemException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse constraintSystemException(SystemException ex) {
        log.error("System exception, errorDesc: {}", ex.getCause().getMessage());
        return new ApiErrorResponse(500, ex.getMessage(), ex);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse constraintException(Exception ex) {
        log.error("Unknow exception", ex);
        return new ApiErrorResponse(500, ex.getMessage(), ex);
    }

/*    @ExceptionHandler(value = {FeignException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse constraintFeignException(Exception ex) {
        log.error("Feign exception", ex);
        return new ApiErrorResponse(BusinessExceptionEnum.FEIGN_EXCEPTION.getCode(), ex.getMessage(), ex);
    }*/

    /**
     * Handle violation exception
     * 验证异常处理message提示
     *
     * @param cve
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse error(ConstraintViolationException cve) {
        log.error("Params violation excetion", cve);

        Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
        List<String> errorMsg = new LinkedList<>();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            for (ConstraintViolation<?> violation : constraintViolations) {
                errorMsg.add(violation.getMessage());
            }
        }
        return new ApiErrorResponse(1111, errorMsg.toString(), cve);
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse error(BindException bindException) {
        log.error("Params bind exception", bindException);
        List<String> errorMsg = new LinkedList<>();
        for (ObjectError objectError : bindException.getAllErrors()) {
            FieldError fieldError = (FieldError) objectError;
            errorMsg.add(fieldError.getField() + fieldError.getDefaultMessage());
        }
        return new ApiErrorResponse(2222, errorMsg.toString(), bindException);
    }

    /**
     * @param ex
     * @return
     * @RequestBody json数据接收绑定异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String errorMessage = fieldError.getField() + fieldError.getDefaultMessage();
        return new ApiErrorResponse(3333, errorMessage, ex);
    }

}
