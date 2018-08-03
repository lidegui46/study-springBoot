package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.connectionFactory;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.stereotype.Service;

/**
 * @author： ldg
 * @create date： 2018/7/30
 */
@Service
public class RabbitMQConnectionContext {
    public String getConnectionStr(ConnectionFactory connectionFactory) {
        return String.format("%s_%s_%s",connectionFactory.getHost(),connectionFactory.getPort(),connectionFactory.getVirtualHost());
    }
}
