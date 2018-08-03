package ldg.study.springboot.messagequeue.rabbit.ack.producer.service;

/**
 * @author： ldg
 * @create date： 2018/7/26
 */
public interface CartProducerService {
    void sendMessageByAddCart(Object customParam);

    void sendMessageByDeleteCart(Object customParam);
}
