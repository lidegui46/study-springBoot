package ldg.study.springboot.messagequeue.rabbit.ack.producer.retry;

import ldg.study.springboot.messagequeue.rabbit.ack.producer.configuration.rabbitMQ.correlation.CorrelationDataDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * RabbitMQ Retry 上下文
 * <pre>
 *     重试机制可考虑：RetryTemplate
 *
 *     要求消费者处理业务时保证幂等性
 * </pre>
 * 此上下文未实现，后期继续实现，参考：docment/retryExample/rabbitmq-access-master.zip
 *
 * @author： ldg
 * @create date： 2018/7/30
 */
@Service
public class RabbitMQRetryContext {
    private static ConcurrentMap<String, CorrelationDataDto> map = new ConcurrentHashMap<>();
    private boolean stop = false;

    public void put(String key, CorrelationDataDto value) {
        if (!(map.containsKey(key) || map.containsValue(value))) {
            map.put(key, value);
            //startRetry();
        }
    }

    public void remove(String key) {
        if (map.containsKey(key)) {
            map.remove(key);
        }
    }

    private void startRetry() {
        new Thread(() -> {
            while (!stop) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long now = System.currentTimeMillis();

                for (Map.Entry<String, CorrelationDataDto> entry : map.entrySet()) {
                    CorrelationDataDto value = entry.getValue();

                    //查找connection
                    //发送
                    //发送成功：删除，发送失败：继续发送

                }
            }
        }).start();
    }

}
