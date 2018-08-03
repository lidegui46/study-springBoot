package ldg.study.springboot.messagequeue.rabbit.ack.producer.service;

/**
 * @author： ldg
 * @create date： 2018/7/24
 */
public interface OrderProducerService {
    void sendMessageByCreateOrder(Object customParam);

    void sendMessageByPayOrder(Object customParam);
}
