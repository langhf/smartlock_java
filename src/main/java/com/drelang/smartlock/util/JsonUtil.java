package com.drelang.smartlock.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
  *  Jackson json 序列化和反序列化工具
  * Created by Drelang on 2019/01/10
  */
public class JsonUtil {
    // 定义 Jackson 对象
    public static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     *  将对象转为 json 字符串
     * @param data 对象
     * @return
     */
    public static String objectToJson(Object data) {
        try{
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  将 json 结果集转化为对象
     * @param jsonData json 数据
     * @param beanType 对象中的 object 类型
     * @param <T>
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  将 json 数据转换成 pojo 对象 list
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try{
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
