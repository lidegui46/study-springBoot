package ldg.study.springboot.redis.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;


@Configuration
@EnableCaching
public class RedisTemplateConfiguration {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.maxIdle}")
    private int maxIdle;
    @Value("${spring.redis.maxTotal}")
    private int maxTotal;
    @Value("${spring.redis.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${spring.redis.testOnBorrow}")
    private boolean testOnBorrow;


    @Value("${spring.redis2.host}")
    private String host2;
    @Value("${spring.redis2.port}")
    private int port2;
    @Value("${spring.redis2.timeout}")
    private int timeout2;
    @Value("${spring.redis2.maxIdle}")
    private int maxIdle2;
    @Value("${spring.redis2.maxTotal}")
    private int maxTotal2;
    @Value("${spring.redis2.maxWaitMillis}")
    private int maxWaitMillis2;
    @Value("${spring.redis2.testOnBorrow}")
    private boolean testOnBorrow2;


    /**
     * key 生成工具
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /***************************  Default Redis Template ***************************************/
    @Bean
    public JedisConnectionFactory defaultRedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setTimeout(timeout);
        factory.setPoolConfig(poolCofig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow));
        factory.afterPropertiesSet();

        System.out.println("redis baseConfig=========" + host);
        return factory;
    }

    @Bean(name = "defaultRedisTemplate")
    public StringRedisTemplate defaultRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(defaultRedisConnectionFactory());
        //设置序列化工具，这样ReportBean不需要实现Serializable接口
        template.setValueSerializer(getSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager defaultCacheManager(RedisTemplate defaultRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(defaultRedisTemplate);
        // Number of seconds before expiration. Defaults to unlimited (0)
        // 设置key-value超时时间
        cacheManager.setDefaultExpiration(10);
        return cacheManager;
    }

    /***************************  Article Redis Template ***************************************/
    @Bean
    public JedisConnectionFactory articleRedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host2);
        factory.setPort(port2);
        factory.setTimeout(timeout2);
        factory.setPoolConfig(poolCofig(maxIdle2, maxTotal2, maxWaitMillis2, testOnBorrow2));
        factory.afterPropertiesSet();
        //RedisConnectionFactory factory = jedis;
        System.out.println("redis baseConfig=========" + host2);
        return factory;
    }

    @Bean(name = "articleRedisTemplate")
    public StringRedisTemplate articleRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(articleRedisConnectionFactory());
        //设置序列化工具，这样ReportBean不需要实现Serializable接口
        template.setValueSerializer(getSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager articleCacheManager(RedisTemplate articleRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(articleRedisTemplate);
        // Number of seconds before expiration. Defaults to unlimited (0)
        //设置key-value超时时间
        cacheManager.setDefaultExpiration(10);
        return cacheManager;
    }

    public JedisPoolConfig poolCofig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        return poolCofig;
    }

    private Jackson2JsonRedisSerializer getSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }
}
