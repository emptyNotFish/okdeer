package com.okdeer.mall.redis.queue;

/**
 * Created by Lenovo on 2017/11/8.
 */
public abstract interface RedisMessageProcess {
    public abstract void process(Object paramObject);
}
