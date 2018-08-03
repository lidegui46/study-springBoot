package ldg.study.springboot.messagequeue.rabbit.support.consumer;

import ldg.study.springboot.messagequeue.rabbit.configuration.RabbitMqWithDirectExhangeConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Profile("direct")
@Component
public class DirectConsumer {

    @RabbitListener(queues = RabbitMqWithDirectExhangeConfiguration.QUEUE_NAME_APPLE)
    public void consumeMessage1(String message)
    {
        System.out.println(message);
    }

    @RabbitListener(queues = RabbitMqWithDirectExhangeConfiguration.QUEUE_NAME_BANANA)
    public void consumeMessage2(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues = RabbitMqWithDirectExhangeConfiguration.QUEUE_NAME_ORANGE)
    public void consumeMessage3(String message) {
        System.out.println(message);
    }

}
