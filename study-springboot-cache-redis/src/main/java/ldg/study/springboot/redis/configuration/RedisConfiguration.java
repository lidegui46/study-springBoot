package ldg.study.springboot.redis.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author foursix
 * @since 2017-09-11
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    private Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);

    /*******************             第一个Redis配置 Begin            *********************/
    /**
     * 读取Application.properties中的配置
     */
    @Value("${redis1.database}")
    private int database;

    @Value("${redis1.host}")
    private String host;

    @Value("${redis1.port}")
    private int port;

    @Value("${redis1.password}")
    private String password;

    @Value("${redis1.timeout}")
    private int timeout;

    @Value("${redis1.pool.maxTotal}")
    private int maxTotal;

    @Value("${redis1.pool.maxIdle}")
    private int maxIdle;

    @Value("${redis1.pool.minIdle}")
    private int minIdle;

    @Value("${redis1.pool.maxWaitMillis}")
    private int maxWaitMillis;

    @Value("${redis1.pool.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${redis1.pool.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun;

    @Value("${redis1.pool.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${redis1.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis1.pool.testOnReturn}")
    private boolean testOnReturn;

    @Value("${redis1.pool.testWhileIdle}")
    private boolean testWhileIdle;

    /**
     * 通过Bean注册后，调用方可已注入的jedisPool名称调用
     * <p>
     *     调用方式：
     *      Autowired
     *      private JedisPool redisPool1;
     * </p>
     * @return Redis 池
     */
    @Bean(name = "redisPool1")
    public JedisPool redisPoolFactory1() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);

        logger.info("Redis注入成功!地址{}:{},database:{}", host, port, database);
        return new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
    }
    /*******************             第一个Redis配置 End            *********************/



    /*******************             第二个Redis配置 Begin            *********************/

    @Value("${redis2.database}")
    private int database2;

    @Value("${redis2.host}")
    private String host2;

    @Value("${redis2.port}")
    private int port2;

    @Value("${redis2.password}")
    private String password2;

    @Value("${redis2.timeout}")
    private int timeout2;

    @Value("${redis2.pool.maxTotal}")
    private int maxTotal2;

    @Value("${redis2.pool.maxIdle}")
    private int maxIdle2;

    @Value("${redis2.pool.minIdle}")
    private int minIdle2;

    @Value("${redis2.pool.maxWaitMillis}")
    private int maxWaitMillis2;

    @Value("${redis2.pool.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis2;

    @Value("${redis2.pool.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun2;

    @Value("${redis2.pool.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis2;

    @Value("${redis2.pool.testOnBorrow}")
    private boolean testOnBorrow2;

    @Value("${redis2.pool.testOnReturn}")
    private boolean testOnReturn2;

    @Value("${redis2.pool.testWhileIdle}")
    private boolean testWhileIdle2;

    /**
     * 通过Bean注册后，调用方可已注入的jedisPool名称调用
     * <p>
     *     调用方式：
     *      Autowired
     *      private JedisPool redisPool2;
     * </p>
     * @return Redis池
     */
    @Bean(name = "redisPool2")
    public JedisPool redisPoolFactory2() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal2);
        jedisPoolConfig.setMaxIdle(maxIdle2);
        jedisPoolConfig.setMinIdle(minIdle2);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis2);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis2);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun2);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis2);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow2);
        jedisPoolConfig.setTestOnReturn(testOnReturn2);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle2);

        logger.info("Redis注入成功!地址{}:{},database:{}", host2, port2, database2);
        return new JedisPool(jedisPoolConfig, host2, port2, timeout2, password2, database2);
    }
    /*******************             第二个Redis配置 End            *********************/


    /*******************             第三个Redis配置 Begin          *********************/

    /*******************             第三个Redis配置 End            *********************/
}
