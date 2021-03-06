package ldg.study.springboot.messagequeue.rabbit.support.consumer;

import ldg.study.springboot.messagequeue.rabbit.configuration.RabbitMqWithFanoutExhangeConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("fanout")
@Component
public class FanoutConsumer {

    @RabbitListener(queues = RabbitMqWithFanoutExhangeConfiguration.QUEUE_NAME_APPLE)
    public void consumeMessage1(String message){
        System.out.println(message);
    }

    @RabbitListener(queues = RabbitMqWithFanoutExhangeConfiguration.QUEUE_NAME_BANANA)
    public void consumeMessage2(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues = RabbitMqWithFanoutExhangeConfiguration.QUEUE_NAME_ORANGE)
    public void consumeMessage3(String message) {
        System.out.println(message);
    }
}
