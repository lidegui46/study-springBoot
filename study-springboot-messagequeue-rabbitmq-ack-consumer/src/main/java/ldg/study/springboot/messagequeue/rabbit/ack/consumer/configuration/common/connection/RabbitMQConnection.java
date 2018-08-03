package ldg.study.springboot.messagequeue.rabbit.ack.consumer.configuration.common.connection;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ Connection
 *
 * @author： ldg
 * @create date： 2018/7/27
 */
@Service
public class RabbitMQConnection {

    public ConnectionFactory createConnectionFactory(String host, int port, String user, String password, String virtalHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);
        //采用默认虚拟主机，     思考：怎么创建其它虚拟主机 和  使用其它虚拟主机
        connectionFactory.setVirtualHost(virtalHost);
        return connectionFactory;
    }
}
