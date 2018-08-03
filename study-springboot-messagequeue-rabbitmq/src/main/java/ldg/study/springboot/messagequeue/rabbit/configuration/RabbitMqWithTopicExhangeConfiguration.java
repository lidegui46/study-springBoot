package ldg.study.springboot.messagequeue.rabbit.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 通配符 topic 交换器
 *
 * @author： ldg
 * @create date： 2018/7/23
 */
//@Profile("topic") //profile 配置为 topic时启动配置项目
@Configuration
public class RabbitMqWithTopicExhangeConfiguration {

    public final static String QUEUE_NAME_APPLE = "topic.exchange.business.apple";
    public final static String QUEUE_NAME_BANANA = "topic.exchange.business.banana";
    public final static String QUEUE_NAME_ORANGE = "topic.exchange.business.orange";
    public final static String ROUTING_KEY_APPLE = "fruit.apple";
    public final static String ROUTING_KEY_BANANA = "fruit.banana";
    public final static String ROUTING_KEY_ORANGE = "fruit.orange";
    public final static String ROUTING_KEY = "fruit.apple";
    public final static String ROUTING_KEY_2 = "fruit.*";
    public final static String ROUTING_KEY_3 = "fruit#";
    public final static String EXCHANGE_NAME = "topic.exchange";

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

    // 创建一个 topic 类型的交换器
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // 使用路由键（routingKey）把队列（Queue）绑定到交换器（Exchange）
    @Bean
    public Binding bindingApple(Queue queueApple, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueApple).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingBanana(Queue queueBanana, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueBanana).to(topicExchange).with(ROUTING_KEY_2);
    }

    @Bean
    public Binding bindingOrange(Queue queueOrange, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueOrange).to(topicExchange).with(ROUTING_KEY_3);
    }

    @Bean("topicExchange")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
