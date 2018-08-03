package ldg.study.springboot.messagequeue.active.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * ActiveMQ 生产者
 *
 * @author： ldg
 * @create date： 2018/5/3
 */
@Component
public class ActiveMQProdducer {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQProdducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    /*//JmsMessagingTemplate对JmsTemplate进行了封装
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;*/

    /**
     * @param destination
     * @param message
     */
    public void sendMessage(Destination destination, String message) {
        try {
            this.jmsTemplate.convertAndSend(destination, message);
        } catch (JmsException ex) {
            logger.error("发送ActiveMQ消息失败", ex);
        }
    }
}
