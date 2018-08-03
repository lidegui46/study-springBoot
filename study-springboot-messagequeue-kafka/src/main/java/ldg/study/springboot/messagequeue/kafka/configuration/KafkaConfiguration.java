package ldg.study.springboot.messagequeue.kafka.configuration;

import ldg.study.springboot.messagequeue.kafka.baseConfig.service.Consumer;
import ldg.study.springboot.messagequeue.kafka.baseConfig.service.Producer;
import ldg.study.springboot.messagequeue.kafka.baseConfig.support.DefaultConsumer;
import ldg.study.springboot.messagequeue.kafka.baseConfig.support.DefaultProducer;
import ldg.study.springboot.messagequeue.kafka.consumer.base.KafkaConsumerMain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author foursix
 * @since 2017/11/3
 */
@Component
public class KafkaConfiguration {
    /**
     * Kafka地址，多个以逗号分隔
     */
    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    /**
     * topics 支持多个，逗号分隔
     */
    @Value("${kafka.topics}")
    private String kafkaTopics;

    /**
     * 消费消息时，是否自动提交
     */
    @Value("${kafka.enable.auto.commit}")
    private String enableAutoCommit;

    /**
     * 消息方Group，一个Topic有多个Group消息，同一个Group只消息一次
     */
    @Value("${kafka.group.id}")
    private String groupId;

    /**
     * 配置
     *
     * @return
     */
    private Properties buildKafkaProperties() {
        Properties properties = new Properties();
        properties.setProperty("enable.auto.commit", enableAutoCommit);
        properties.setProperty("group.id", groupId);
        properties.setProperty("bootstrap.servers", bootstrapServers);
        return properties;
    }

    /**
     * kafka 消费
     *
     * @return
     */
    @Bean(name = "kafkaConsumerMain")
    public KafkaConsumerMain kafkaConsumerMain() {
        return new KafkaConsumerMain(kafkaConsumer(), kafkaTopics);
    }

    /**
     * kafka消费者，通过注解可调用此消费
     *
     * @return
     */
    @Bean(name = "kafkaConsumer")
    public Consumer kafkaConsumer() {
        return new DefaultConsumer(buildKafkaProperties());
    }

    /**
     * kafka 生产者，通过注解可调用此生产
     *
     * @return
     */
    @Bean(name = "kafkaProducer")
    public Producer kafkaProducer() {
        return new DefaultProducer(buildKafkaProperties());
    }

}
