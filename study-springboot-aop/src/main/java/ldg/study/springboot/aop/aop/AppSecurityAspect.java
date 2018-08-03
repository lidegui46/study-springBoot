package ldg.study.springboot.aop.aop;

import ldg.study.springboot.aop.service.TokenManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * author：      ldg
 * create date： 2018/4/17
 */
@Aspect
@Order(2)
@Component
public class AppSecurityAspect {
    private static final String DEFAULT_TOKEN_NAME = "App-Token";

    @Autowired
    private TokenManager defaultTokenManager;

    @Pointcut(value = "execution(public * ldg.study.springboot.aop.web.controller.PointCutController.*(..))")
//要处理的方法，包名+类名+方法名
    public void cut() {

    }

    @Around("cut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {//用于获取类方法
        return execute(joinPoint);
    }

    private Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader(DEFAULT_TOKEN_NAME);
        //此处做Token校验
        /*if (!defaultTokenManager.checkToken(token)) {
            String message = String.format("token [%s] is invalid", token);
            throw new Exception(message);
        }*/
        return joinPoint.proceed();// 调用目标方法
    }
}
