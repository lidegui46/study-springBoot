package ldg.study.springboot.mvc.configuration;

import ldg.study.springboot.mvc.converter.CustomerMappingJackson2HttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * MVC 配置
 * author：      ldg
 * create date： 2018/4/17
 * 参数地址：http://www.importnew.com/21589.html#comment-650356
 */
@Configuration
public class SpringMVCConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 设置跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    /**
     * 转换器
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(new CustomerMappingJackson2HttpMessageConverter());
    }
}
