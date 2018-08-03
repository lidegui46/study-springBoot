package ldg.study.springboot.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * author：      ldg
 * create date： 2018/4/17
 * Aop Aspect
 * 参考地址：
 * Aop原理：http://www.importnew.com/24305.html
 * 案例：http://www.importnew.com/17813.html
 * 参数详解：http://www.importnew.com/17828.html
 */
@Aspect     // AOP
@Order(1)   // AOP 通知顺序
@Component
public class HttpLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpLogAspect.class);

    /**
     * value说明：
     * public：方法权限
     * *：返回参数
     * 扫描的包路径或包路径方法
     */
    @Pointcut(value = "execution(public * ldg.study.springboot.aop.web.controller.PointCutController.*(..))")
//要处理的方法，包名+类名+方法名
    public void cut() {

    }

    /*@Pointcut(value = "execution(public * ldg.study.springboot.aop.web.*.*.*(..)) && args(param)",argNames = "param")//要处理的方法，包名+类名+方法名
    public void cut(String param) {

    }*/

    /*
    //1、限制参数、类、注解等；
    @Before(value = "execution(public ..*(..))&& args(param) && target(bean) && @annotation(secure)", argNames="jp,param,bean,secure")
    public void before5(JoinPoint jp, String param, IPointcutService pointcutService, Secure secure) {}
    */


    /*
    //2、支持多个切入点
    @Pointcut(value="args(param)", argNames="param")
    private void pointcut1(String param){}

    @Pointcut(value="@annotation(secure)", argNames="secure")
    private void pointcut2(Secure secure){}

    @Before(value = "pointcut1(param) && pointcut2(secure)", argNames="param, secure")
    public void before6(JoinPoint jp, String param, Secure secure) {}
    */

    @Before("cut()")//在调用上面 @Pointcut标注的方法前执行以下方法
    public void doBefore(JoinPoint joinPoint) {//用于获取类方法
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("------------------------ aop pointcut before message: begin -------------------------");
        //time
        logger.info("time = {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        //url
        logger.info("url ={}", request.getRequestURI());
        //method
        logger.info("method = {}", request.getMethod());
        //ip
        logger.info("ip = {}", request.getRemoteAddr());
        //类方法
        logger.info("class_method = {}", joinPoint.getSignature().getDeclaringTypeName() + '.' + joinPoint.getSignature().getName());//获取类名及类方法
        //参数
        logger.info("args = {}", joinPoint.getArgs());
        logger.info("------------------------ aop pointcut before message: end -------------------------");
    }

    @AfterReturning(returning = "obj", pointcut = "cut()")//在调用上面 @Pointcut标注的方法后执行。用于获取返回值
    public void doAfterReturning(Object obj) {
        logger.info("------------------------ aop pointcut after message: begin -------------------------");
        logger.info("response={}", obj);
        logger.info("------------------------ aop pointcut after message: end -------------------------");
    }

    @AfterThrowing(value = "cut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        String message = new StringBuilder("method ").append(joinPoint.getSignature().getName()).append(" error").toString();
        logger.error("{},", message, ex);
    }
}
