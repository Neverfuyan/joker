package com.wj.springcloud.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;


/**
 * @author ：actor
 * @date ：Created in 2021/7/28 14:29
 * @description：redis配置类
 * @modified By：
 * @version: $
 */
@Component
@Configurable
public class RedisConfig {


    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> templete = new RedisTemplate<>();
        //添加连接工厂
        templete.setConnectionFactory(factory);
        //传入值序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //采用string的序列化方式
        templete.setKeySerializer(stringRedisSerializer);
        //hash也采用string的序列化方式
        templete.setHashKeySerializer(stringRedisSerializer);
        //存储值也采用string的序列化方式
        templete.setValueSerializer(stringRedisSerializer);
        //hash存储的值也采用string的序列化方式
        templete.setHashValueSerializer(stringRedisSerializer);
        templete.afterPropertiesSet();
        return templete;
    }

}
