package ldg.study.springboot.messagequeue.active.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * ActiveMQ 异步监听模式 消费者
 *
 * @author： ldg
 * @create date： 2018/5/3
 */
@Component
public class ActiveMQListener {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQListener.class);

    /**
     * @param message
     */
    @JmsListener(destination = "mytest.business")
    public void receiveQueue(String message) {

    }
}
