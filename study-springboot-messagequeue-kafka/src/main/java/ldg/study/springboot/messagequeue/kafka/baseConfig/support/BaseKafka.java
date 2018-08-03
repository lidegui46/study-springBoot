package ldg.study.springboot.messagequeue.kafka.baseConfig.support;

import ldg.study.springboot.messagequeue.kafka.baseConfig.DefaultConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author
 * @since 2017-09-13
 */
public abstract class BaseKafka {

    protected Properties loadPropertiesFromFile() throws IOException {
        Properties props = new Properties();
        InputStream in = DefaultProducer.class.getClassLoader().getResourceAsStream(DefaultConfig.DEFAULT_PROPERTIES);
        if (in != null) {
            props.load(in);
        }
        return props;
    }

}
