package ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.common.template;

import com.alibaba.fastjson.JSONObject;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.connectionFactory.RabbitMQConnectionContext;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.correlation.CorrelationDataDto;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.correlation.CorrelationType;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.correlation.RabbitMQCorrelationData;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.constant.RedisConfig;
import ldg.study.springboot.messagequeue.rabbit.ack.producer.retry.RabbitMQRetryContext;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Map;

/**
 * RabbitMQ Template 公共组件
 *
 * @author： ldg
 * @create date： 2018/7/27
 */
@Service
public class RabbitMQTemplate {

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitMQCorrelationData rabbitMQCorrelationData;

    @Autowired
    private RabbitMQConnectionContext rabbitMQConnectionContext;

    @Autowired
    private RabbitMQRetryContext rabbitMQRetryContext;

    /**
     * 创建Rabbmit Template
     * <pre>
     *     如果同一个项目有多个ConnectionFactory,则每个ConnectionFactory都需要创建一个新的RabbitMQAdmin，否则会导致绑定的交换机和队列乱串
     * </pre>
     *
     * @param connectionFactory RabbitMQ模板绑定到指定的connectionFactory
     * @return 待创建的模板
     */
    public RabbitTemplate createTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //rabbitTemplate.setRetryTemplate();

        boolean hasOpenTransacte = false;
        if (hasOpenTransacte) {
            rabbitTemplate.setChannelTransacted(true);
            //事务代码待确认
            RabbitTransactionManager transactionManager = new RabbitTransactionManager(connectionFactory);
            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        } else {
            // 必须设置为 true，不然当 发送到交换器成功，但是没有匹配的队列，不会触发 ReturnCallback 回调
            // 而且 ReturnCallback 比 ConfirmCallback 先回调，意思就是 ReturnCallback 执行完了才会执行 ConfirmCallback
            rabbitTemplate.setMandatory(true);

            // 设置 ConfirmCallback 回调
            rabbitTemplate.setConfirmCallback(getConfirmCallback());

            // 设置 ReturnCallback 回调
            // 如果发送到交换器成功，但是没有匹配的队列，就会触发这个回调
            rabbitTemplate.setReturnCallback(getReturnCallback());

            // 设置 RecoveryCallback 回调
            //rabbitTemplate.setRecoveryCallback();
        }

        return rabbitTemplate;
    }


    /**
     * <pre>
     * 触发回调 前提条件：
     *      成功发送到交换器成功，但是没有匹配的队列
     *
     * 产生原因：
     *      通过“RabbitMQ管理平台”手工删除“队列”
     *
     * 解决方案：
     *      重启工程，自动创建交换机 和 队列
     * </pre>
     *
     * @return
     */
    private RabbitTemplate.ReturnCallback getReturnCallback() {
        return (message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println(String.format("return callback ：not find routable queue messages,\r\n   message : %s\r\n   replyCode : %s\r\n   replyText : %s\r\n   exchange : %s\r\n   routingKey : %s "
                    , message, replyCode, replyText, exchange, routingKey));

            try {
                //从header中 获取业务数据
                MessageProperties messageProperties = message.getMessageProperties();
                Map<String, Object> headers = messageProperties.getHeaders();
                Assert.isTrue(headers.containsKey(RedisConfig.NOT_FIND_QUEUE_REDIS_KEY), String.format("未从header中找到 %s", RedisConfig.NOT_FIND_QUEUE_REDIS_KEY));
                String notFindQueueParamStr = (String) headers.get(RedisConfig.NOT_FIND_QUEUE_REDIS_KEY);

                //业务数据 转换为 实体对象（2次取数据，以redis数据为准）
                CorrelationDataDto correlationDataDto = rabbitMQCorrelationData.get(notFindQueueParamStr);
                String correlationStr = correlationDataDto.getCorrelation().toString();
                String messageId = correlationDataDto.getMessageId();
                HashOperations hashOperations = redisTemplate.opsForHash();
                Assert.isTrue(hashOperations.hasKey(correlationStr, messageId), String.format("未从redis中找到 correlation : %s messageId : %s", correlationStr, messageId));
                String correlationDataStr = (String) hashOperations.get(correlationStr, correlationDataDto.getMessageId());
                Assert.hasText(correlationDataStr, String.format("未从redis中找到 correlation : %s messageId : %s", correlationStr, messageId));
                correlationDataDto = rabbitMQCorrelationData.get(correlationDataStr);
                Assert.notNull(correlationDataDto, String.format("从redis中获取的数据转换为correlationData时为空;\r\n   correlationDataStr : %s", correlationDataStr));

                // 根据 redis 取出的数据，重新设置 returnCallback = true,回写Reids；作用：标记未找到指定队列
                correlationDataDto.setHasReturnCallback(true);
                hashOperations.put(correlationStr, messageId, rabbitMQCorrelationData.setReturnCallIsTrue(correlationDataDto));
            } catch (Exception ex) {
                System.out.println("return callback exception");
                ex.printStackTrace();
            }
        };
    }

    /**
     * 消息发送 成功 或 失败 都要回调
     * <pre>
     *     区别：
     *          匹配交换机成功：ack = true
     *              如果发送到交换器成功，但是没有匹配的队列（比如说取消了绑定），ack 返回值为还是 true （这是一个坑，需要注意）
     *
     *          匹配交换机失败：ack = false
     *              如果发送到交换器都没有成功（比如说删除了交换器），ack 返回值为 false
     *
     * </pre>
     *
     * @return
     */
    private RabbitTemplate.ConfirmCallback getConfirmCallback() {
        return (correlationData, ack, cause) -> {
            try {
                //业务数据 转换为 实体对象（2次取数据，以redis数据为准）
                CorrelationDataDto correlationDataDto = rabbitMQCorrelationData.get(correlationData.getId());
                String correlationDataStr = (String) redisTemplate.opsForHash().get(correlationDataDto.getCorrelation().toString(), correlationDataDto.getMessageId());
                correlationDataDto = rabbitMQCorrelationData.get(correlationDataStr);
                correlationDataStr = correlationDataDto.getCorrelation().toString();
                String messageId = correlationDataDto.getMessageId();

                if (redisTemplate.opsForHash().hasKey(correlationDataStr, messageId)) {
                    redisTemplate.opsForHash().delete(correlationDataStr, messageId);
                }

                //消费者是否成功消费
                boolean hasConsumerSuccess = ack && !correlationDataDto.isHasReturnCallback();
                System.out.println(String.format("confirm callback：request;\r\n   correlationData : %s\r\n   ack : %s\r\n   messageId : %s\r\n   consumeResult : %s\r\n   cause : %s"
                        , correlationData, ack, messageId, hasConsumerSuccess, cause));
                if (!hasConsumerSuccess) {
                    System.out.println("consume fail");
                    rabbitMQRetryContext.put(messageId, rabbitMQCorrelationData.get(correlationDataStr));
                }
            } catch (Exception ex) {
                System.out.println("confirm callback exception");
                ex.printStackTrace();
            }
        };
    }


    /**
     * 转换并发送消息
     *
     * @param rabbitTemplate  rabbit Template 模板，由调用方先注入后再传入
     * @param correlationType 业务操作类型，详见枚举“CorrelationType”
     * @param exchangeName    交换机名称
     * @param routeKeyName    路由键名称
     * @param customParam     自定义参数，提供给消息方使用
     */
    public void convertAndSend(RabbitTemplate rabbitTemplate, CorrelationType correlationType, String exchangeName
            , String routeKeyName, Object customParam) {
        String messageId = java.util.UUID.randomUUID().toString();
        String correlationTypeStr = correlationType.toString();
        String customParamStr = JSONObject.toJSONString(customParam);
        String correlationDataStr = rabbitMQCorrelationData.create(rabbitMQConnectionContext.getConnectionStr(rabbitTemplate.getConnectionFactory())
                , exchangeName, routeKeyName, correlationType, messageId, customParamStr);

        System.out.println("");
        System.out.println(String.format("convertAndSend request param：\r\n   CorrelationType : %s\r\n   exchangeName : %s\r\n   routeKeyName : %s\r\n   customParam : %s\r\n   messageId : %s"
                , correlationTypeStr, exchangeName, routeKeyName, correlationDataStr, messageId));

        // 先把消息存储到 redis
        redisTemplate.opsForHash().put(correlationTypeStr, messageId, correlationDataStr);

        // 发送到创建订单
        rabbitTemplate.convertAndSend(exchangeName, routeKeyName, customParamStr, (message) -> {
            MessageProperties messageProperties = message.getMessageProperties();
            /*
             * 通过Header存储RabbitMQ内部交互数据
             * 场景：当“指定队列”在服务器中未找到时，RabbitMQ自动触发“ReturnCallback”，通过程序标记“已触发ReturnCallback”，
             *      发送消息的“消息确认”时，对消息标注“未发送成功”，原因：未找到队列，详见"ReturnCallback"和"ConfirmCallback"
             */
            messageProperties.setHeader(RedisConfig.NOT_FIND_QUEUE_REDIS_KEY.toString(), correlationDataStr);
            messageProperties.setMessageId(messageId);
            return message;
        }, new CorrelationData(correlationDataStr));
    }
}
