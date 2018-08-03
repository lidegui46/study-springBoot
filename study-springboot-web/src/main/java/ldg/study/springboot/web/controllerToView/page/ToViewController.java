package ldg.study.springboot.web.controllerToView.page;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *     查看thymeleaf 配置
 *     import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
 *
 *     如：
 *     #thymeleaf start
 *      spring.thymeleaf.mode=HTML5
 *      spring.thymeleaf.encoding=UTF-8
 *      spring.thymeleaf.content-type=text/html
 *      #开发时关闭缓存,不然没法看到实时页面
 *      spring.thymeleaf.cache=false
 *      #thymeleaf end
 * </p>
 * @author foursix
 * @since 2017/11/15
 */
@Controller
@RequestMapping(value = "/controllerToView")
public class ToViewController {
    /**
     * 访问地址：http://localhost:8084/controllerToView/index
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String controllerToHtml(Model model) {
        model.addAttribute("name","Ryan");
        return "controllerToHtml";
    }

}
