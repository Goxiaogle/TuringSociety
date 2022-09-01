package com.goxiaoge.turingsociety.configure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.goxiaoge.turingsociety.enums.Status;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigInteger;

@Configuration
public class JacksonConfiguration {

    /**
     * 对 Status 状态枚举类的 JSON 转换器
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer statusSerializer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializerByType(
                Status.class, new JsonSerializer<Status>() {
                    @Override
                    public void serialize(Status value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                        gen.writeStartObject();
                        gen.writeNumberField("value", value.getValue());
                        gen.writeStringField("comment", value.getComment());
                        gen.writeEndObject();
                    }
                }
        );
    }

    /**
     * 将 18 位数字转为 18 位字符串，以避免前端 js 精度问题
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer longFieldToString() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(BigInteger.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
        };
    }


}
