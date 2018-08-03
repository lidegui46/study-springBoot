package ldg.study.springboot.dubbo.consumer.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author foursix
 * @since 2017/9/19
 */
@Configuration
@ImportResource(value = {"classpath*:/spring/dubbo-consume.xml"})
public class DubboConsumerConfiguration {
    //自动导入配置文件“dubbo-consume.xml”
}
