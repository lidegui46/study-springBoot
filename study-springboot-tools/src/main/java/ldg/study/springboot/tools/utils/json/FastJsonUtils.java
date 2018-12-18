package ldg.study.springboot.tools.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 序列化工具
 *
 * @author foursix
 * @since 2017/11/23
 */
public final class FastJsonUtils {
    /**
     * 序列化参数
     */
    private static final SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullNumberAsZero};

    /**
     * 对象转换成json 支持list,map,array
     *
     * @param obj
     * @return
     */
    public static String objToJson(Object obj) {
        return JSON.toJSONString(obj, features);
    }


    /**
     * json 转换成对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T jsonToObj(String json, Class<?> clazz) {
        return (T) JSON.parseObject(json, clazz);
    }
}