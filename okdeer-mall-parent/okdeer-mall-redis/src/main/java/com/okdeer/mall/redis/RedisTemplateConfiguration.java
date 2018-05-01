package com.okdeer.mall.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 不知道为什么要设置那么多的序列化版本
 * 应该针对不同的情况设置一个即可
 * 先注释 后面再看什么情况
 * 是redisconfig中三种情况中的第一种AutoConfig加载方法
 * 这里只是重写了redis序列化的方式
 * Created by Lenovo on 2017/8/15.
 */
//@Configuration
public class RedisTemplateConfiguration {
    /**
     * Reids-Hash Serializer<String,String,Kryo>
     *
     * @param redisConnectionFactory
     * @return RedisTemplate bean with name "hashRedisTemplate"
     */
    //@Bean
    public RedisTemplate<String, Object> hashRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new KryoRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 采用redis默认的序列化方式对key value进行序列化操作
     *
     * @param redisConnectionFactory
     * @return
     */
    //@Bean
    public RedisTemplate<String, Object> defaultRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * key,value 都采用String序列化
     *
     * @param redisConnectionFactory redisConnectionFactory
     * @return RedisTemplate bean with name "strRedisTemplate"
     */
    //@Bean
    public RedisTemplate<String, Object> strRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**
     * key,value 都采用String序列化; value采用lz4压缩.
     *
     * @param redisConnectionFactory redisConnectionFactory
     * @return RedisTemplate bean with name "strCompressedRedisTemplate"
     */
   // @Bean
    public RedisTemplate<String, Object> strCompressedRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new CompressedStringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**
     * key采用String序列化; value采用kryo序列化.
     *
     * @param redisConnectionFactory redisConnectionFactory
     * @return RedisTemplate bean with name "strRedisTemplate"
     */
    //@Bean
    public RedisTemplate<String, Object> strKryoRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new KryoRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**
     * @see RedisTemplateConfiguration#strKryoRedisTemplate(RedisConnectionFactory)
     */
    @Deprecated
   // @Bean
    public RedisTemplate<String, Object> strJdkCompressedRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new KryoRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
