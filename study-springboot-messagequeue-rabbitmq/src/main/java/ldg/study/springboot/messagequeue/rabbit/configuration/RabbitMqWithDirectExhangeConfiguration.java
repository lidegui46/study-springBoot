package ldg.study.springboot.messagequeue.rabbit.configuration;

import ldg.study.springboot.messagequeue.rabbit.contain.RabbitMQConfig;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author： ldg
 * @create date： 2018/7/23
 */
//@Profile("direct") //profile 配置为 direct 时启动配置项目
@Configuration
public class RabbitMqWithDirectExhangeConfiguration {

    public final static String QUEUE_NAME_APPLE = "direct.exchange.business.apple";
    public final static String QUEUE_NAME_BANANA = "direct.exchange.business.banana";
    public final static String QUEUE_NAME_ORANGE = "direct.exchange.business.orange";
    public final static String ROUTING_KEY_APPLE = "fruit.apple";
    public final static String ROUTING_KEY_BANANA = "fruit.banana";
    public final static String ROUTING_KEY_ORANGE = "fruit.orange";
    public final static String EXCHANGE_NAME = "direct.exchange";

    // 创建队列
    @Bean
    public Queue queueApple() {
        return new Queue(QUEUE_NAME_APPLE);
    }

    @Bean
    public Queue queueBanana() {
        return new Queue(QUEUE_NAME_BANANA);
    }

    @Bean
    public Queue queueOrange() {
        return new Queue(QUEUE_NAME_ORANGE);
    }

    // 创建一个 direct 类型的交换器
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding bindingApple(Queue queueApple, DirectExchange directExchange) {
        return BindingBuilder.bind(queueApple).to(directExchange).with(ROUTING_KEY_APPLE);
    }

    @Bean
    public Binding bindingBanana(Queue queueBanana, DirectExchange directExchange) {
        return BindingBuilder.bind(queueBanana).to(directExchange).with(ROUTING_KEY_BANANA);
    }

    @Bean
    public Binding bindingOrange(Queue queueOrange, DirectExchange directExchange) {
        return BindingBuilder.bind(queueOrange).to(directExchange).with(ROUTING_KEY_ORANGE);
    }

    @Bean("directExchange")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
