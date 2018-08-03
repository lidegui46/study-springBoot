package ldg.study.springboot.redis.Common;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 一个应用中存在多个Redis配置
 * <p>这是第一个配置</p>
 *
 * @author ldg on 2017/9/6.
 */
@Service
public class Jedis1Visitor {

    private final Logger logger = LoggerFactory.getLogger(Jedis1Visitor.class);

    /**
     * 字段名与"Redis1Configuration"自动注入的名称相同,则读取对应配置的数据，如:redisPool1
     */
    @Autowired
    private JedisPool redisPool1;

    /**
     * 获取连接
     *
     * @return
     */
    private Jedis getClient() {
        try {
            return this.redisPool1.getResource();
        } catch (Exception e) {
            throw new JedisConnectionException(e);
        }
    }


    /**
     * set Hash
     *
     * @param activityId 活动编号
     * @param list list
     * @param pilingType pilingType
     * @param type type
     * @return hash值
     */
    public Long setHash(Integer activityId, Integer pilingType, String type, List<Integer> list) {
        String key = String.format("%s:%s:%s", type, activityId, pilingType);
        try (Jedis client = getClient()) {
            for (Integer id : list) {
                client.hset(key, "buyer_id:" + id, id.toString());
            }
            return 1L;
        } catch (Exception ex) {
            logger.error(String.format("获取Redis数据异常,JedisVisitor - getHash，key:%s,msg:%s", key, ex.getMessage()));
            return 0L;
        }
    }

    /**
     * set Hash
     *
     * @param key   key
     * @param field field
     * @param value value
     * @return hash值
     */
    public Long setHash(String key, String field, String value) {
        try (Jedis client = getClient()) {
            return client.hset(key, field, value);
        } catch (Exception ex) {
            logger.error(String.format("获取Redis数据异常,JedisVisitor - getHash，key:%s,msg:%s", key, ex.getMessage()));
            return 0L;
        }
    }

    /**
     * 获取Hash
     *
     * @param key key
     * @return hash值
     */
    public Map<String, String> getHash(String key) {
        try (Jedis client = getClient()) {
            return client.hgetAll(key);
        } catch (Exception ex) {
            logger.error(String.format("获取Redis数据异常,JedisVisitor - getHash，key:%s,msg:%s", key, ex.getMessage()));
            return Collections.EMPTY_MAP;
        }
    }

    /**
     * 获取Hash
     *
     * @param keys key集合
     * @return key对应的value
     */
    public Map<String, Map<String, String>> getHashs(List<String> keys) {
        Map<String, Map<String, String>> map = Maps.newHashMap();
        try (Jedis client = getClient()) {
            for (String key : keys) {
                map.put(key, client.hgetAll(key));
            }
        } catch (Exception ex) {
            logger.error(String.format("获取Redis数据异常,JedisVisitor - getHashs，key:%s,msg:%s", keys, ex.getMessage()));
        }
        return map;
    }
}
