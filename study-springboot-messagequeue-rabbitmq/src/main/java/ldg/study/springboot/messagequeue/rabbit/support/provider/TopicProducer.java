package ldg.study.springboot.messagequeue.rabbit.support.provider;

import ldg.study.springboot.messagequeue.rabbit.configuration.RabbitMqWithTopicExhangeConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class TopicProducer {

    @Autowired
    @Qualifier("order")
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        int count = 1;
        for (int i = 0; i < count; i++) {
            String value = "apple " + i;
            System.out.println(value);
            rabbitTemplate.convertAndSend(RabbitMqWithTopicExhangeConfiguration.EXCHANGE_NAME
                    , RabbitMqWithTopicExhangeConfiguration.ROUTING_KEY_APPLE, value);
        }
    }

}
