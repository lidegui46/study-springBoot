package ldg.study.springboot.tools.utils.key;

import java.util.Date;
import java.util.UUID;

/**
 * @author foursix
 * @since 2018/1/19
 */
public class IDGeneratorUtils {
    /**
     * @return 生成唯一标识（32位）
     */
    public static String generateID() {
        return generateID(System.currentTimeMillis());
    }

    /**
     * 根据指定时间生成主键，切忌不能用此方法来生成主键插入数据库
     * @param date 时间
     * @return 生成唯一标识（32位）
     */
    public static String generateID(Date date) {
        return generateID(date.getTime());
    }

    /**
     * 根据指定时间生成主键
     * @param time 毫秒级的时间
     * @return 生成唯一标识（32位）
     */
    private static String generateID(long time) {
        String result = Long.toHexString(time);
        result += UUID.randomUUID();
        result = result.replaceAll("-", "");
        return result.substring(0, 32);
    }
}
