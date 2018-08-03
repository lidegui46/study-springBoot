package ldg.study.springboot.tools.web.api;

import com.alibaba.fastjson.JSONObject;
import ldg.study.springboot.tools.web.dto.RequestBodyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author foursix
 * @since 2017/9/19
 */
@RestController
@RequestMapping(value = "/tools")
public class RestTemplateController {
    @Autowired
    private RestTemplate restTemplate;

    private String url = "http://localhost:8081/tools/response/";

    /**
     * 内部类必须是static
     */
    public static class ResultResponse {
        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    @GetMapping(value = "get")
    public void get() {
        /**
         * 1、通过url 地址传递参数
         * 2、返回数据类型 String
         */
        String result = restTemplate.getForObject(url + "get?name=name_1&group=group_1&age=11", String.class);
        System.out.println(result);

        /**
         * 1、通过url 地址访问
         * 2、通过Map传递参数无效
         * 2、返回数据类型：Pojo
         */
        ResultResponse result2 = restTemplate.getForObject(url + "get", ResultResponse.class, getParam());
        System.out.println(result2.getResult());
    }

    @GetMapping(value = "postForUrlParam")
    public void postForUrlParam() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //设置自定义 header
        headers.add("token", "123456789");
        //headers.put(HttpHeaders.COOKIE,cookieList); //将cookie放入header

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        /**
         * 1、通过url 地址传递参数
         * 2、返回数据类型 String
         */
        String result = restTemplate.postForObject(url + "postForUrlParam?name=name_1&group=group_1&age=11"
                , entity, String.class);
        System.out.println(result);

        /**
         * 1、通过url 地址访问
         * 2、通过Map传递参数无效
         * 2、返回数据类型：Pojo
         */
        ResultResponse result2 = restTemplate.postForObject(url + "postForUrlParam", entity, ResultResponse.class, getParam());
        System.out.println(result2.getResult());
    }

    @GetMapping(value = "postForBody")
    public void postForBody() {
        RequestBodyDto requestBodyDto = new RequestBodyDto();
        requestBodyDto.setName("name_1");
        requestBodyDto.setGroup("group_1");
        requestBodyDto.setAge(11);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("token", "123456789");

        String jsonBody = JSONObject.toJSONString(requestBodyDto);

        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);

        /**
         * 1、通过url 地址访问
         * 2、返回数据类型 String
         */
        String result = restTemplate.postForObject(url + "postForBody", entity, String.class);
        System.out.println(result);

        /**
         * 1、通过url 地址访问
         * 2、返回数据类型：Pojo
         */
        ResultResponse result2 = restTemplate.postForObject(url + "postForBody", entity, ResultResponse.class);
        System.out.println(result2.getResult());
    }

    private Map getParam() {
        Map<String, String> urlParamMap = new HashMap<String, String>();
        urlParamMap.put("name", "name_1");
        urlParamMap.put("group", "group_1");
        urlParamMap.put("age", "11");
        return urlParamMap;
    }
}
