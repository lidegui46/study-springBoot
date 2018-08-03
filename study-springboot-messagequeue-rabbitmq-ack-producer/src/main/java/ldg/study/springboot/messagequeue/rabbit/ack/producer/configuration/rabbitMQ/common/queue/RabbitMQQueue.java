package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ 队列 公共组件
 *
 * @author： ldg
 * @create date： 2018/7/27
 */
@Service
public class RabbitMQQueue {
    /**
     * 创建队列
     *
     * @param rabbitMQAdmin 申明队列，注：通过待申明的队列（rabbitAdmin）绑定到 ConnectionFacotry
     * @param queueName     队列名称
     * @return 待创建队列
     */
    public Queue createQueue(RabbitAdmin rabbitMQAdmin, String queueName) {
        Queue queue = new Queue(queueName);

        // 注：创建队列时，必须双向指定rabbitAdmin，否则队列无法绑定到对应的ConnectionFactory
        // 以下为双向绑定
        queue.setAdminsThatShouldDeclare(rabbitMQAdmin);
        rabbitMQAdmin.declareQueue(queue);

        return queue;
    }
}
