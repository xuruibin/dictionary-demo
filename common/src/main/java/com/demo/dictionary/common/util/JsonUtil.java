package com.demo.dictionary.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * @author Dora B
 */
@SuppressWarnings("JavaDoc")
public class JsonUtil {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        // date format
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }

    private static final SerializerFeature[] features = {
            // set the default value instead of null
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullStringAsEmpty
    };

    /**
     * Convert object to json string
     *
     * @param object
     * @return
     */
    public static String toFeaturesJson(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * Convert object to json string
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * Convert json string to object
     *
     * @param json
     * @return
     */
    public static Object parse(String json) {
        return JSON.parse(json);
    }

    /**
     * Convert json string to T object
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T parse(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }

    /**
     * Convert json string to Map<String, T>
     *
     * @param json
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> toMap(String json) {
        return (Map<String, T>) JSONObject.parseObject(json);
    }

    /**
     * Convert json string to List<T>
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> cls) {
        return JSON.parseArray(json, cls);
    }

}