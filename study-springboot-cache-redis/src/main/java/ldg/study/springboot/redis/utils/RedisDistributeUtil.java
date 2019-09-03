package ldg.study.springboot.redis.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * 【单机】 基于 Redis 分布式锁
 * <p>
 * 【思考】怎么实现重入锁，包括：获取锁 和 释放锁
 * <pre>
 *      原理：
 *          获取锁：SetNx
 *          删除锁：
 *              1、过期
 *              2、手动获取并删除
 *                  该操作分为2步，非原子性，可使用 lua 脚本语言的脚本操作保证原子性操作
 * </pre>
 * Author:  ldg
 * Date:    2019/8/31 12:39
 * Desc:    this is file description
 */
public class RedisDistributeUtil {

    /**********************************************************/
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String RELEASE_SCRIPT =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    /**
     * 尝试获取锁
     *
     * @param jedis      jedis 连接
     * @param lockKey    锁 key，如：商品编号
     * @param lockObject 获取锁对象，如：当前线程ID； ***  作用：确保是当前获取锁的线程释放锁（防止其它获取锁误删） ***
     * @param expireTime 超时时间，单位：毫秒
     */
    public static boolean tryGetLock(Jedis jedis, String lockKey, String lockObject, int expireTime) {
        String result = jedis.set(lockKey, getLockObject(lockObject), SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        return LOCK_SUCCESS.equals(result);
    }

    /**
     * 释放锁
     *
     * @param jedis      jedis 连接
     * @param lockKey    锁 key，如：商品编号
     * @param lockObject 获取锁对象，如：当前线程ID； ***  作用：确保是当前获取锁的线程释放锁（防止其它获取锁误删） ***
     * @return
     */
    public static boolean releaseLock(Jedis jedis, String lockKey, String lockObject) {
        Object result = jedis.eval(RELEASE_SCRIPT, Collections.singletonList(lockKey)
                , Collections.singletonList(getLockObject(lockObject)));
        return RELEASE_SUCCESS.equals(result);
    }
    /**********************************************************/


    /**********************************************************/
    /**
     * SetNx And Expire 脚本
     */
    private static final RedisScript SETNX_AND_EXPIRE_REDIS_SCRIPT;
    private static final StringBuilder SETNX_REDIS_SCRIPT_SB = new StringBuilder()
            .append("if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then")
            .append("   redis.call('expire',KEYS[1],tonumber(ARGV[2]))")
            .append("   return true")
            .append("else")
            .append("   return false")
            .append("end");
    /**
     * Release 脚本
     */
    private static final RedisScript RELEASE_REDIS_SCRIPT;
    private static final StringBuilder RELEASE_REDIS_SCRIPT_SB = new StringBuilder()
            .append("if redis.call('get', KEYS[1]) == ARGV[1] then")
            .append("   return redis.call('del', KEYS[1])")
            .append("else")
            .append("   return 0")
            .append("end");

    static {
        SETNX_AND_EXPIRE_REDIS_SCRIPT = getBooleanRedisScript(SETNX_REDIS_SCRIPT_SB);
        RELEASE_REDIS_SCRIPT = getIntegerRedisScript(RELEASE_REDIS_SCRIPT_SB);
    }

    private static RedisScript getBooleanRedisScript(StringBuilder script) {
        return new DefaultRedisScript(script.toString(), Boolean.class);
    }

    private static RedisScript getIntegerRedisScript(StringBuilder script) {
        return new DefaultRedisScript(script.toString(), Integer.class);
    }

    /**
     * 尝试获取锁
     *
     * @param stringRedisTemplate redis 连接模板
     * @param lockKey             锁 key，如：商品编号
     * @param lockObject          获取锁对象，如：当前线程ID； ***  作用：确保是当前获取锁的线程释放锁（防止其它获取锁误删） ***
     * @param expireTime          超时时间
     */
    public static boolean tryGetLockByLua(StringRedisTemplate stringRedisTemplate, String lockKey, String lockObject, int expireTime) {
        return (boolean) stringRedisTemplate.execute(SETNX_AND_EXPIRE_REDIS_SCRIPT
                , Collections.singletonList(lockKey)
                , getLockObject(lockObject), String.valueOf(expireTime));
    }

    /**
     * 释放锁
     *
     * @param stringRedisTemplate redis 连接模板
     * @param lockKey             锁 key，如：商品编号
     * @param lockObject          获取锁对象，如：当前线程ID； ***  作用：确保是当前获取锁的线程释放锁（防止其它获取锁误删） ***
     * @return
     */
    public static boolean releaseLockByLua(StringRedisTemplate stringRedisTemplate, String lockKey, String lockObject) {
        Integer result = (Integer) stringRedisTemplate.execute(RELEASE_REDIS_SCRIPT
                , Collections.singletonList(lockKey)
                , getLockObject(getLockObject(lockObject)));
        return result > 0;
    }

    /**********************************************************/

    /**
     * 获取 锁对象
     *
     * @param lockObject 获取锁对象，如：当前线程ID； ***  作用：确保是当前获取锁的线程释放锁（防止其它获取锁误删） ***
     * @return
     */
    private static String getLockObject(String lockObject) {
        if (lockObject == null || "".equals(lockObject)) {
            lockObject = Thread.currentThread().getId() + "";
        }
        return lockObject;
    }

    public static void setInventory(Jedis jedis, String key, Integer inventory) {
        try {
            jedis.set(key, String.valueOf(inventory));
        } finally {
            jedis.close();
        }
    }

    public static Integer getInventory(Jedis jedis, String key) {
        try {
            String val = jedis.get(key);
            if (val != null) {
                return Integer.parseInt(val);
            }
        } finally {
            jedis.close();
        }
        return 0;
    }

    public static void decrInventory(Jedis jedis, String key) {
        try {
            jedis.decr(key);
        } finally {
            jedis.close();
        }
    }

    /*************************************************************************************/
}
