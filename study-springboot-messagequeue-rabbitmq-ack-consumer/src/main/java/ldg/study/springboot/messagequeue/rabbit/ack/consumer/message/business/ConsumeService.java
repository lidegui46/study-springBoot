package ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.business;

/**
 * @author： ldg
 * @create date： 2018/7/25
 */
public interface ConsumeService {

    void execute(String param);
}
