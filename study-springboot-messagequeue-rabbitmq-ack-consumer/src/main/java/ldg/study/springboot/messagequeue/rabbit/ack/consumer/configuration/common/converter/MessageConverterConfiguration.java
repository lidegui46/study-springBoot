package ldg.study.springboot.messagequeue.rabbit.ack.consumer.configuration.common.converter;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 转换器
 *
 * @author： ldg
 * @create date： 2018/7/27
 */
@Configuration
public class MessageConverterConfiguration {
    /**
     * 消息转换器
     * <pre>
     *     用于对消息内容进行转换，可根据实际业务数据格式指定不同的转换器
     * </pre>
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new SimpleMessageConverter();
    }
}
