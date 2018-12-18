package ldg.study.springboot.tools.web.api;

import ldg.study.springboot.tools.utils.json.FastJsonUtils;
import ldg.study.springboot.tools.web.dto.TestRequestDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author foursix
 * @since 2017/11/10
 */
@RestController
@RequestMapping(value = "/tools/response")
public class ResponseController {
    public class ListObj {
        private List<String> orderIds;

        public List<String> getOrderIds() {
            return orderIds;
        }

        public void setOrderIds(List<String> orderIds) {
            this.orderIds = orderIds;
        }
    }

    @RequestMapping(value = "/postListParam")
    public String postListParam(ListObj d) {
        return "";
    }

    @RequestMapping(value = "/s")
    public String s(String s) {
        return s;
    }

    @PostMapping(value = "/postForBody")
    public String postAndBody(@RequestBody TestRequestDto testRequest, HttpServletRequest request) {
        System.out.println("");
        System.out.println("method: postForBody --------------------------------------------------");
        System.out.println("token: " + request.getHeader("token"));
        System.out.println("request: " + FastJsonUtils.objToJson(testRequest));
        return "success";
        //return "{'result':'success'}";
    }

    @PostMapping(value = "/postForUrlParam")
    public String postForUrlParam(HttpServletRequest request,@RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "group", required = false) String group
            , @RequestParam(value = "age", required = false) String age) {
        System.out.println("");
        System.out.println("method: postForUrlParam --------------------------------------------------");
        System.out.println("token: " + request.getHeader("token"));
        System.out.println("request: " + name + " " + group + " " + age);
        //return "success";
        return "{'result':'success'}";
    }

    @GetMapping(value = "/get")
    public String get(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "group", required = false) String group
            , @RequestParam(value = "age", required = false) String age) {
        System.out.println(name + " " + group + " " + age);
        //return "success";
        return "{'result':'success'}";
    }
}
