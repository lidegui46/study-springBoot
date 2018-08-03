package ldg.study.springboot.messagequeue.rabbit.ack.producer.dto;

import java.io.Serializable;

/**
 * 自定义消息元数据
 */
public class RabbitMetaMessageDto implements Serializable {

    /**
     * 是否触发 returnCallback
     * <pre>
     *     当换到交换器，但是未匹配到队列时，触发returnCallback
     * </pre>
     */
    private boolean returnCallback = false;

    /**
     * reids存储key
     */
    private String redisKey;

    /**
     * 承载原始消息数据数据
     */
    private Object metaData;

    public boolean isReturnCallback() {
        return returnCallback;
    }

    public void setReturnCallback(boolean returnCallback) {
        this.returnCallback = returnCallback;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public Object getMetaData() {
        return metaData;
    }

    private RabbitMetaMessageDto() {
        //do nothing
    }

    public RabbitMetaMessageDto(String redisKey, Object metaData) {
        this.redisKey = redisKey;
        this.metaData = metaData;
    }
}
