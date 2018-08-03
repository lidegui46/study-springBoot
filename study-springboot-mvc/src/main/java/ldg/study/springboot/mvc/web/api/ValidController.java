package ldg.study.springboot.mvc.web.api;

import com.alibaba.fastjson.JSONObject;
import ldg.study.springboot.mvc.dto.UserResponseParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * author：      ldg
 * create date： 2018/4/17
 */
@RestController
@RequestMapping(value = "/mvc/valid")
public class ValidController {
    /**
     * 访问地址：http://127.0.0.1:8084/mvc/valid/test
     *
     * @return
     */
    @PostMapping(value = "test")
    public String test(@RequestBody @Valid UserResponseParam userParam) {
        return JSONObject.toJSONString(userParam);
    }
}
