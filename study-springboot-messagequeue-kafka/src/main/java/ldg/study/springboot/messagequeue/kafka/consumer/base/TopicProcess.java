package ldg.study.springboot.messagequeue.kafka.consumer.base;

/**
 * topic 进程
 *
 * @author foursix
 * @since 2017/11/3
 */
public interface TopicProcess {
    /**
     * 获取Topic名称
     *
     * @return
     */
    String getTopicName();

    /**
     * 执行消息
     *
     * @param message 消息内容
     */
    void execute(String message);
}
