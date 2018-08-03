package ldg.study.springboot.web.controllerToView.support;

import ldg.study.springboot.tools.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by foursix on 2018/1/31.
 *
 * 全局变量用于传递给前端页面
 * 作用：全局拦截
 */
@ControllerAdvice("ldg.study.springboot.web.controllerToView.page")
public class PageAdvice {
    @ModelAttribute
    public void setServerTime(Model model) {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
        model.addAttribute("jServerTime", System.currentTimeMillis() / 1000);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");
    }

    /**
     * 设置要捕获的异常，并作出处理
     * 注意：这里可以返回试图，也可以放回JSON，这里就当做一个Controller使用
     *
     * @param request {@link NativeWebRequest}
     * @param e {@link Exception}
     * @return {@link ResponseEntity<Response>}
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Response> processUnauthenticatedException(NativeWebRequest request, Exception e) {
        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出Exception异常时执行");
        return Response.error(e);
    }
}
