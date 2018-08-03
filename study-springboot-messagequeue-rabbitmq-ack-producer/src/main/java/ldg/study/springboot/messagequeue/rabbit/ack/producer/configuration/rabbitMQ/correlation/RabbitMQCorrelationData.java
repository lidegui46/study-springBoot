package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.correlation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author： ldg
 * @create date： 2018/7/27
 */
@Service
public class RabbitMQCorrelationData {
    /**
     * 创建
     * <pre>
     *     格式：业务类型__消息编号__是否回调__自定义
     *     创建时，是否回调 = false
     * </pre>
     *
     * @param correlation
     * @param messageId
     * @param customParam
     * @return
     */
    public String create(String connectionFactoryName, String exchangeName, String routeKeyName, CorrelationType correlation, String messageId, String customParam) {
        Assert.hasText(messageId, "messageId is empty");

        CorrelationDataDto correlationDataDto = newInstance(connectionFactoryName, exchangeName, routeKeyName, correlation, messageId, false, customParam);

        return JSONObject.toJSONString(correlationDataDto);
    }

    /**
     * 已回调
     * <pre>
     *     格式：业务类型__消息编号__是否回调__自定义
     *     创建时，是否回调 = true
     * </pre>
     *
     * @param correlationDataStr 存储数据
     * @return
     */
    public String setReturnCallIsTrue(String correlationDataStr) {
        Assert.hasText(correlationDataStr, "correlationDataStr is empty");
        CorrelationDataDto correlationDataDto = get(correlationDataStr);
        correlationDataDto.setHasReturnCallback(true);
        return JSONObject.toJSONString(correlationDataDto);
    }


    public String setReturnCallIsTrue(CorrelationDataDto correlationData) {
        Assert.notNull(correlationData, "correlationData is null");
        correlationData.setHasReturnCallback(true);
        return JSONObject.toJSONString(correlationData);
    }

    public CorrelationDataDto get(String correlationDataStr) {
        Assert.hasText(correlationDataStr, "correlationDataStr is empty");

        return JSON.parseObject(correlationDataStr, CorrelationDataDto.class);
    }

    /**
     * 实例化业务数据
     *
     * @param exchangeName     交换机名称
     * @param routeKeyName     路由键名称
     * @param correlation      数据业务类型
     * @param messageId        消息编号
     * @param isReturnCallback 是否已回调
     * @param customParam      自定参数
     * @return
     */
    private CorrelationDataDto newInstance(String connectionFactoryName, String exchangeName, String routeKeyName, CorrelationType correlation
            , String messageId, boolean isReturnCallback, String customParam) {
        CorrelationDataDto dto = new CorrelationDataDto();
        dto.setConnectionFactoryName(connectionFactoryName);
        dto.setExchangeName(exchangeName);
        dto.setRouteKeyName(routeKeyName);
        dto.setCorrelation(correlation);
        dto.setMessageId(messageId);
        dto.setHasReturnCallback(isReturnCallback);
        dto.setCustomParam(customParam);
        return dto;
    }
}
