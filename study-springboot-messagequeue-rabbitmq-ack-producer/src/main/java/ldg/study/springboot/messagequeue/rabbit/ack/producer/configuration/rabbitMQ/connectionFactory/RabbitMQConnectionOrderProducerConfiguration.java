package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.connectionFactory;

import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.admin.RabbitMQAdmin;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.template.RabbitMQTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 订单 生产者 连接工厂
 *
 * @author： ldg
 * @create date： 2018/7/24
 */
@Configuration("ConnOrderProducerConfiguration")
public class RabbitMQConnectionOrderProducerConfiguration {
    @Value("${spring.rabbitmq.order.host}")
    private String host;
    @Value("${spring.rabbitmq.order.port}")
    private int port;
    @Value("${spring.rabbitmq.order.username}")
    private String user;
    @Value("${spring.rabbitmq.order.password}")
    private String password;

    @Value("${spring.rabbitmq.order.publisher-confirms}")
    private boolean publisherConfirms;
    @Value("${spring.rabbitmq.order.publisher-returns}")
    private boolean publisherReturns;

    @Autowired
    private RabbitMQTemplate rabbitMQTemplate;

    @Autowired
    private RabbitMQAdmin rabbitMQAdmin;

    //坑点：cartConnectionFactory 只能通过@Autowired注入，否则在多ConnectionFactory环境下通过参数注入默认是第一个注入的连接
    @Autowired
    @Qualifier("orderProducerConnectionFactory")
    private ConnectionFactory connectionFactory;

    /**
     * 生产者连接工厂
     * 注：如果一个项目启动多个连接工厂时，需要区分连接工厂名称,否则相同的连接工厂只会触发一个
     *
     * @return
     */
    @Primary //坑点：一个环境有多个ConnectionFactory时，必须指定一个，@Primary放在此处时，会创建N个配置的Connection  @思考：不什么配置在此处时创建N个配置连接
    @Bean("orderProducerConnectionFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);
        //采用默认虚拟主机，     思考：怎么创建其它虚拟主机 和  使用其它虚拟主机
        connectionFactory.setVirtualHost("/");

        // 设置 生产者 confirms（生产者发送消息时，是否confirm）
        connectionFactory.setPublisherConfirms(publisherConfirms);

        // 设置 生产者 Returns（生产者发送消息时，是否return）
        connectionFactory.setPublisherReturns(publisherReturns);

        return connectionFactory;
    }


    /**
     * 创建  RabbitMQ Template
     * <pre>
     *      坑点：每个ConnectionFactory对应一个Template
     * </pre>
     *
     * @return 已创建的RabbitMQ Template
     */
    @Bean("orderProducerRabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
        //坑点：cartConnectionFactory 只能通过@Autowired注入，否则在多ConnectionFactory环境下通过参数注入默认是第一个注入的连接
        return rabbitMQTemplate.createTemplate(connectionFactory);
    }


    /**
     * 创建 RabbitMQ Admin
     * <pre>
     *      坑点：每个ConnectionFactory对应一个RabbtAdmin
     * </pre>
     *
     * @return 已创建的Rabbit Admin
     */
    @Bean("orderProducerRabbitAdmin")
    public RabbitAdmin rabbitAdmin() {
        //坑点：cartConnectionFactory 只能通过@Autowired注入，否则在多ConnectionFactory环境下通过参数注入默认是第一个注入的连接
        return rabbitMQAdmin.createAdmin(connectionFactory);
    }
}
