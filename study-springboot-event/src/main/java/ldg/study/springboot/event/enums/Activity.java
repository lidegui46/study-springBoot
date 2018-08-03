package ldg.study.springboot.event.enums;

/**
 * 活动枚举
 *
 * @author foursix
 * @since 2017/10/13
 */
public class Activity {
    /**
     * 活动类型
     */
    public enum Type {
        NULL(-1, "无"), Explosive(1, "爆品"), Seckill(2, "秒杀");
        /**
         * 活动类型编码
         */
        private final int code;
        /**
         * 活动类型描述
         */
        private final String desc;

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        /**
         * 活动类型
         *
         * @param code 类型编码
         * @param desc 类型描述
         */
        Type(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        /**
         * 获取指定的活动类型
         *
         * @param code
         * @return
         */
        public static Type of(int code) {
            for (Type type : Type.values()) {
                if (type.getCode() == code) {
                    return type;
                }
            }
            return Type.NULL;
        }

    }
}
