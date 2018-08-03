package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.admin;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ Admin 公共组件
 *
 * @author： ldg
 * @create date： 2018/7/27
 */
@Service
public class RabbitMQAdmin {
    /**
     * 创建申请
     * <pre>
     *     如果同一个项目有多个ConnectionFactory,则每个ConnectionFactory都需要创建一个新的RabbitMQAdmin，否则会导致绑定的交换机和队列乱串
     * </pre>
     *
     * @param connectionFactory 待申明（交易机、队列、绑定）绑定到指定的connectionFactory
     * @return 待创建的申明
     */
    public RabbitAdmin createAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
