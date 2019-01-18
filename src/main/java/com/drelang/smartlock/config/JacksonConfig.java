package com.drelang.smartlock.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
  * Jackson 配置类
 *  json 不返回 null 的字段
  * Created by Drelang on 2019/01/16
  */
@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 通过该方法对 mapper 对象进行设置，所有序列化的对象都将按该规则进行序列化
        // Inlucde.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为空 (""),或者为 NULL 都不序列化，则返回的 json 是没有这个字段的。这样对移动端会更省流量
        // Include.NON_NULL 属性为 NULL 不序列化，就是为 null 的字段不参加序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 字段保留，将 null 值转为 ""
//        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//                jsonGenerator.writeString("");
//            }
//        });
        return objectMapper;
    }
}
