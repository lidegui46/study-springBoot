package ldg.study.springboot.redis.configuration;

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
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

/**
 * @author foursix
 * @since 2017/9/21
 */
/*
@Configuration
@PropertySource(value = "classpath:/redis.properties")
@EnableCaching
public class test {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;


    @Value("${spring.redis2.host}")
    private String host2;
    @Value("${spring.redis2.port}")
    private int port2;
    @Value("${spring.redis2.timeout}")
    private int timeout2;


    @Bean
    public KeyGenerator wiselyKeyGenerator(){
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

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setTimeout(timeout); //设置连接超时时间

        System.out.println("redis baseConfig========="+host);
        return factory;
    }

    @Bean
    public JedisConnectionFactory redisArticleConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host2);
        factory.setPort(port2);
        factory.setTimeout(timeout2); //设置连接超时时间
        System.out.println("redis baseConfig========="+host2);
        return factory;
    }


    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // Number of seconds before expiration. Defaults to unlimited (0)
        cacheManager.setDefaultExpiration(10); //设置key-value超时时间
        return cacheManager;
    }

    @Bean(name = "redisDefaultTemplate")
    public StringRedisTemplate testRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        //setSerializer(template); //设置序列化工具，这样ReportBean不需要实现Serializable接口
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "redisArticleTemplate")
    public StringRedisTemplate articleRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisArticleConnectionFactory());
        //setSerializer(template); //设置序列化工具，这样ReportBean不需要实现Serializable接口
        template.afterPropertiesSet();
        return template;
    }


    public RedisConnectionFactory connectionFactory(String hostName, int port ) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(hostName);
        jedis.setPort(port);

        //jedis.setPoolConfig(poolCofig(maxIdle, maxTotal, maxWaitMillis,testOnBorrow));
        // 初始化连接pool
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;
        return factory;
    }

    public JedisPoolConfig poolCofig(int maxIdle, int maxTotal,
                                     long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        return poolCofig;
    }

    private void setSerializer(StringRedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
   }

}*/
