package com.okdeer.mall.redis;

import commons.Constants;
import commons.KryoUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class KryoRedisSerializer implements RedisSerializer<Object> {

    private Class<?> objectClass;

    private boolean compress;

    private int bufferSize = KryoUtils.DEFAULT_BUFFER_SIZE;

    public KryoRedisSerializer() {
        this(Object.class, false);
    }

    public KryoRedisSerializer(boolean compress) {
        this(Object.class, compress);
    }

    public KryoRedisSerializer(Class<?> objectClass) {
        this(objectClass, false);
    }

    public KryoRedisSerializer(Class<?> objectClass, boolean compress) {
        this.objectClass = objectClass;
        this.compress = compress;
    }

    public KryoRedisSerializer withBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null) return Constants.EMPTY_ARRAY;

        return Object.class.equals(objectClass) ? KryoUtils.serializerWithClass(o, bufferSize, compress) :
                KryoUtils.serializer(o, bufferSize, compress);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) return null;

        return Object.class.equals(objectClass) ? KryoUtils.deserializerWithClass(bytes, compress) :
                KryoUtils.deserializer(bytes, objectClass, compress);
    }
}

