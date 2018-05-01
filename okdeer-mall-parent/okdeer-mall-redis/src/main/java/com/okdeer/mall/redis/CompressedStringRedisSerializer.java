package com.okdeer.mall.redis;

import commons.CompressUtils;
import org.springframework.data.redis.serializer.RedisSerializer;

public class CompressedStringRedisSerializer implements RedisSerializer<String> {

    @Override
    public String deserialize(byte[] bytes) {
        return CompressUtils.decompressAsString(bytes);
    }

    @Override
    public byte[] serialize(String string) {
        return CompressUtils.compressString(string);
    }
}
