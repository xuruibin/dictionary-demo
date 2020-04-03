package com.demo.dictionary.common.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author Dora B
 */
@SuppressWarnings("unchecked")
@Slf4j
public class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // 对象字段全部列入
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);

        // 取消默认转换timestamps形式
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 忽略空bean转json的错误
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        // 统一日期格式yyyy-MM-dd HH:mm:ss
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 忽略在json字符串中存在,但是在java对象中不存在对应属性的情况
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static <T> String toJsonString(T object) {
        if (object == null) {
            return "";
        }
        try {
            return object instanceof String ? (String) object : mapper.writeValueAsString(object);
        } catch (Exception e) {
            System.out.println("Parse object to String error");
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        if (json == null || json.equals("") || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) json : mapper.readValue(json, clazz);
        } catch (IOException e) {
            System.out.println("Parse String to Object error");
            e.printStackTrace();
            return null;
        }
    }
}
