package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ Topic 交换机 公共组件
 *
 * @author： ldg
 * @create date： 2018/7/27
 */
@Service
public class RabbitMQTopicExchange {

    /**
     * 创建交换机
     *
     * @param rabbitMQAdmin 申明交换机，注：通过待申明的交换机（rabbitAdmin）绑定到 ConnectionFacotry
     * @param exchangeName  交换机名称
     * @return 待创建的交换机
     */
    public TopicExchange createExchange(RabbitAdmin rabbitMQAdmin, String exchangeName) {
        TopicExchange topicExchange = new TopicExchange(exchangeName);

        // 注：创建交换机时，必须双向指定rabbitAdmin，否则交换机无法绑定到rabbitMQ对应的ConnectionFactory
        // 以下为双向绑定
        rabbitMQAdmin.declareExchange(topicExchange);
        topicExchange.setAdminsThatShouldDeclare(rabbitMQAdmin);

        return topicExchange;
    }


    /**
     * 绑定 交换机 和 队列
     *
     * @param rabbitMQAdmin 申请绑定交换机和队列，注：通过待申明的绑定（rabbitAdmin）绑定到 ConnectionFacotry，此参数必须与Exchange和Queue对应的RabbitMQAdmin是一个
     * @param exchange      待绑定的交换机，注：此参数已绑定的rabbitMQAdmin必须与参数“rabbitMQAdmin”是一个
     * @param queue         待绑定的队列，注：此参数已绑定的rabbitMQAdmin必须与参数“rabbitMQAdmin”是一个
     * @param routeKeyName  交易机 与 队列 绑定的路由键名称
     * @return 待创建的绑定
     */
    public Binding createBinding(RabbitAdmin rabbitMQAdmin, TopicExchange exchange, Queue queue, String routeKeyName) {
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routeKeyName);

        // 注：创建交换机与队列绑定时，必须双向指定rabbitAdmin，否则绑定无法指到到对应的ConnectionFactory
        // 以下为双向绑定
        binding.setAdminsThatShouldDeclare(rabbitMQAdmin);
        rabbitMQAdmin.declareBinding(binding);

        return binding;
    }
}
