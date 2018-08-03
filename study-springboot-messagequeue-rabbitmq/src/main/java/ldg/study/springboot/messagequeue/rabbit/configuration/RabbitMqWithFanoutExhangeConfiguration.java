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
//@Profile("fanout") //profile 配置为 fanout 时启动配置项目
@Configuration
public class RabbitMqWithFanoutExhangeConfiguration {
    public final static String QUEUE_NAME_APPLE = "fanout.exchange.business.apple";
    public final static String QUEUE_NAME_BANANA = "fanout.exchange.business.banana";
    public final static String QUEUE_NAME_ORANGE = "fanout.exchange.business.orange";
    public final static String ROUTING_KEY_APPLE = "fruit.apple";
    public final static String ROUTING_KEY_BANANA = "fruit.banana";
    public final static String ROUTING_KEY_ORANGE = "fruit.orange";
    public final static String EXCHANGE_NAME = "fanout.exchange";

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

    // 创建一个 fanout 类型的交换器
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    // 把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding bindingApple(Queue queueApple, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueApple).to(fanoutExchange);
    }

    @Bean
    public Binding bindingBanana(Queue queueBanana, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueBanana).to(fanoutExchange);
    }

    @Bean
    public Binding bindingOrange(Queue queueOrange, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueOrange).to(fanoutExchange);
    }

    @Bean("funoutExchange")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
