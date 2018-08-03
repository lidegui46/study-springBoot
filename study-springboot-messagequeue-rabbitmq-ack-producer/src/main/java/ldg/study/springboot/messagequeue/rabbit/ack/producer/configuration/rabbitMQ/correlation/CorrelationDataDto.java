package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.correlation;

import java.io.Serializable;

/**
 * 业务数据
 *
 * @author： ldg
 * @create date： 2018/7/27
 */
public class CorrelationDataDto implements Serializable {
    private String connectionFactoryName;
    private String exchangeName;
    private String queueName;
    private String routeKeyName;
    private CorrelationType correlation;
    private String messageId;
    private boolean hasReturnCallback;
    private String customParam;

    public String getConnectionFactoryName() {
        return connectionFactoryName;
    }

    public void setConnectionFactoryName(String connectionFactoryName) {
        this.connectionFactoryName = connectionFactoryName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getRouteKeyName() {
        return routeKeyName;
    }

    public void setRouteKeyName(String routeKeyName) {
        this.routeKeyName = routeKeyName;
    }

    public CorrelationType getCorrelation() {
        return correlation;
    }

    public void setCorrelation(CorrelationType correlation) {
        this.correlation = correlation;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public boolean isHasReturnCallback() {
        return hasReturnCallback;
    }

    public void setHasReturnCallback(boolean hasReturnCallback) {
        this.hasReturnCallback = hasReturnCallback;
    }

    public String getCustomParam() {
        return customParam;
    }

    public void setCustomParam(String customParam) {
        this.customParam = customParam;
    }
}