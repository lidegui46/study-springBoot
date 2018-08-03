package ldg.study.springboot.messagequeue.rabbit.support.provider;

import ldg.study.springboot.messagequeue.rabbit.configuration.RabbitMqWithFanoutExhangeConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class FanoutProducer {

    @Autowired
    @Qualifier("fanoutExchange")
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        int count = 1;
        for (int i = 0; i < count; i++) {
            String value = "apple " + i;
            System.out.println(value);
            rabbitTemplate.convertAndSend(RabbitMqWithFanoutExhangeConfiguration.EXCHANGE_NAME
                    , RabbitMqWithFanoutExhangeConfiguration.ROUTING_KEY_APPLE, value);
        }
    }

}
