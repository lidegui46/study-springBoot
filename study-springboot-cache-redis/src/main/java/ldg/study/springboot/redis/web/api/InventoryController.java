package ldg.study.springboot.redis.web.api;

import ldg.study.springboot.redis.Common.Jedis1Visitor;
import ldg.study.springboot.redis.utils.RedisDistributeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * 库存 控制器
 *
 * @author foursix
 * @since 2017/9/20
 */
@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {
    /**
     * 读取公共Cache服务
     */
    @Autowired
    private Jedis1Visitor jedis1Visitor;

    String key = "goods1";
    String lockObject = "user001";
    private Jedis client = jedis1Visitor.getClient();

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Index() {
        // 尝试获取锁
        while (!RedisDistributeUtil.tryGetLock(client, key, lockObject, 200)) {
        }

        // 获取剩余数量
        int inventory = RedisDistributeUtil.getInventory(client, key);
        if (inventory > 0) {
            // 剩余数量 - 1
            RedisDistributeUtil.decrInventory(client, key);
            System.out.println("现在库存为" + RedisDistributeUtil.getInventory(client, key));
            // 释放锁
            RedisDistributeUtil.releaseLock(client, key, lockObject);
            return "抢购成功";
        } else {
            return "卖完啦";
        }
    }
}
