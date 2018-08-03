package ldg.study.springboot.messagequeue.rabbit.ack.consumer.configuration.connectionFactory;

import ldg.study.springboot.messagequeue.rabbit.ack.consumer.configuration.common.connection.RabbitMQConnection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 连接工厂
 */
@Configuration("ConnOrderConsumerConfiguration")
public class ConnOrderConsumerConfiguration {

    @Value("${spring.rabbitmq.order.host}")
    private String host;
    @Value("${spring.rabbitmq.order.port}")
    private int port;
    @Value("${spring.rabbitmq.order.username}")
    private String user;
    @Value("${spring.rabbitmq.order.password}")
    private String password;

    @Autowired
    private RabbitMQConnection rabbitMQConnection;

    /**
     * 注：如果一个项目启动多个连接工厂时，需要区分连接工厂名称,否则相同的连接工厂只会触发一个
     *
     * @return
     */
    @Bean("orderConsumerConnectionFactory")
    public ConnectionFactory connectionFactory() {
        return rabbitMQConnection.createConnectionFactory(host, port, user, password, "/");
    }
}
