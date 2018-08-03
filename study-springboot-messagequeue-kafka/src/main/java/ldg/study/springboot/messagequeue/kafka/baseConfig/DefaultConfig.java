package ldg.study.springboot.messagequeue.kafka.baseConfig;

/**
 * Kafka默认配置
 *
 * @author
 * @since 2017-09-13
 */
public class DefaultConfig {

    /**
     * 默认Kafka配置文件
     */
    public static final String DEFAULT_PROPERTIES = "kafka.properties";

    /**
     * 默认自动提交消费偏移
     */
    public static final String DEFAULT_ENABLE_AUTO_COMMIT = "true";

    /**
     * 默认提交间隔毫秒
     */
    public static final String DEFAULT_AUTO_COMMIT_INTERVAL_MS = "1000";

    /**
     * 默认会话超时时间
     */
    public static final String DEFAULT_SESSION_TIMEOUT_MS = "30000";

    /**
     * 默认反序列化器
     */
    public static final String DEFAULT_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";

    /**
     * 默认序列化器
     */
    public static final String DEFAULT_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

    /**
     * 生产者提交缓冲内存  默认33554432字节（32MB）
     */
    public static final String DEFAULT_BUFFER_MEMORY = "33554432";

    /**
     * 一次提交字节数
     */
    public static final String DEFAULT_BATCH_SIZE = "16384";

    /**
     * 提交前 等待时间
     */
    public static final String DEFAULT_LINGER_MS = "50";
}
