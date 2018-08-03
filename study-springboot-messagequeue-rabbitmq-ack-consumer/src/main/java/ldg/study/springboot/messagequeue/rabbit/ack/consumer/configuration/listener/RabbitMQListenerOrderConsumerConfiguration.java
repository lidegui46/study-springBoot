package ldg.study.springboot.messagequeue.rabbit.ack.consumer.configuration.listener;

import ldg.study.springboot.messagequeue.rabbit.ack.consumer.constant.RabbitMqConfig;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 监听器 Order
 */
@Configuration("rabbitMQListenerOrderConsumerConfiguration")
public class RabbitMQListenerOrderConsumerConfiguration {

    //坑点：orderConnectionFactory 只能通过@Autowired注入，否则在多ConnectionFactory环境下通过参数注入默认是第一个注入的连接
    @Autowired
    @Qualifier("orderConsumerConnectionFactory")
    private ConnectionFactory connectionFactory;


    /**
     * 方式一：监听容器工厂
     * <pre>
     *     支持以下2种试：
     *          1、所有消息共同使用一个接口实现进行监听，参考“OrderConsumerSupport”；
     *          2、按队列名称进行监听，参考“OrderConsumerCreateOrderSupport” 和 “OrderConsumerPayOrderSupport”
     *     此方式支持手机确认消息
     * </pre>
     *
     * @param listener 设置消息监听器，接收消息类实现ChannelAwareMessageListener 接口，达到对消息共同的临川
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer orderContainer(ChannelAwareMessageListener listener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        //坑点：connectionFactory 只能通过@Autowired注入，否则在多ConnectionFactory环境下通过参数注入默认是第一个注入的连接
        container.setConnectionFactory(connectionFactory);
        //按业务数据格式，指定对应的转换器
        //container.setMessageConverter(new Jackson2JsonMessageConverter());

        // 指定消费者
        container.setMessageListener(listener);
        // 指定监听的队列（注：监听的队列不存在时，rabbitmq提示“Failed to declare queue(s):[队列名称]”异常信息）
        container.setQueueNames(RabbitMqConfig.QUEUE_ORDER_CREATE_NAME, RabbitMqConfig.QUEUE_ORDER_PAY_NAME);

        // 设置消费者的 ack 模式为手动确认模式
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        //Queue每次发送消息数量
        container.setPrefetchCount(5);

        return container;
    }


    /**
     * 方式二：容器工厂
     * <pre>
     *     支持以下1种试：
     *          1、按队列名称进行监听，参考“OrderConsumerCreateOrderSupport” 和 “OrderConsumerPayOrderSupport”;
     *     思考：
     *          此方式接收消息后，怎么进行手工确认消息？？？
     * </pre>
     *
     * @param consumerConnectionFactory 服务器连接工厂
     * @return
     */
    /*@Bean
    public SimpleRabbitListenerContainerFactory orderContainer(ConnectionFactory consumerConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(consumerConnectionFactory);
        //factory.setMessageConverter(new Jackson2JsonMessageConverter());

        //factory.createListenerContainer();//参考上面“SimpleMessageListenerContainer”

        // 设置消费者的 ack 模式为手动确认模式
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        factory.setPrefetchCount(300);

        return factory;
    }*/
}
