package ldg.study.springboot.messagequeue.active.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * ActiveMQ 同步接收消息
 *
 * @author： ldg
 * @create date： 2018/5/3
 */
@Component
public class ActiveMQConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQConsumer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * @param destination 队列
     */
    public void receiveQueue(Destination destination) {
        try {
            Message receiveMessage = jmsTemplate.receive(destination);
            TextMessage textMessage = (TextMessage) receiveMessage;
            String text = textMessage.getText();
        } catch (JMSException ex) {
            logger.error("同步接收消息异常，", ex);
        }
    }
}
