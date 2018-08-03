package ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.listener.consumer2;

import com.rabbitmq.client.Channel;
import ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.business.ConsumeService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法一：实现“ChannelAwareMessageListener”对RabbitMQ进行监听
 * <pre>
 *      此方式对所有消息进行监听
 *      注：如果想对某一个队列进行监听，可以使用注解方式（方式二）
 *
 *      思考：消费者ack成功，但是RabbitMQ服务未收到（原因：服务宕机或网络波动），导致消息未被标记，待消费者的应用下次再启动时，又会再消息一次
 *          解决方案：消费者业务幂等性
 * </pre>
 */
@Component
public class ConsumerSupport implements ChannelAwareMessageListener {
    //private static final String ENCODING = Charset.defaultCharset().name();

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private Map<String, ConsumeService> orderServiceMap = new HashMap<>();

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = 0;

        try {
            MessageProperties messageProperties = message.getMessageProperties();

            // 当前信道的唯一投递标识符，可通过 deliveryTag 消费者告诉 RabbitMQ 收到当前消息
            deliveryTag = messageProperties.getDeliveryTag();

            // 是否重复投递的消息，true：重复投递   false：第一次投递
            Boolean redelivered = messageProperties.getRedelivered();

            // 获取生产者发送的原始消息内容
            Object originalMessage = messageConverter.fromMessage(message);

            // 队列名称
            String queueName = messageProperties.getConsumerQueue();

            // 消息id
            String messageId = messageProperties.getMessageId();

            System.out.println(String.format("consume rabbitmq message:\r\n   customParam : %s\r\n   deliveryTag : %s\r\n   redelivered : %s\r\n   queue : %s\r\n   messageId : %s"
                    , originalMessage, deliveryTag, redelivered, queueName, messageId));

            // ack 方式
            // 代表消费者确认收到当前消息，第二个参数表示一次是否 ack 多条消息
            // channel.basicAck(deliveryTag, false);

            // 代表消费者拒绝一条或者多条消息，第二个参数表示一次是否拒绝多条消息，第三个参数表示是否把当前消息重新入队
            // channel.basicNack(deliveryTag, false, false);

            // 代表消费者拒绝当前消息，第二个参数表示是否把当前消息重新入队
            // channel.basicReject(deliveryTag,false);

            Assert.isTrue(orderServiceMap.containsKey(queueName), String.format("未找到订单服务,name = %s", queueName));

            //FIXME 业务实现耗时尽可能短
            orderServiceMap.get(queueName).execute(originalMessage.toString());

            // 消费者确认收到当前消息，第二个参数表示一次是否 ack 多条消息
            channel.basicAck(deliveryTag, false);
        } catch (Exception ex) {
            ex.printStackTrace();

            //消费者拒绝当前消息，第二个参数表示是否把当前消息重新入队(注：重入队列时，小区循环发送)
            channel.basicReject(deliveryTag, true);
        }
    }
}

