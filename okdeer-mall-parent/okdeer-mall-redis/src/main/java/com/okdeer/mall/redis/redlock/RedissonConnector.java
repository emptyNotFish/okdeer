package com.okdeer.mall.redis.redlock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;

import javax.annotation.PostConstruct;

/**
 * 获取redissonclient连接类
 * Created by Administrator on 2018/5/5 0005.
 */
public class RedissonConnector {

    private RedissonClient redissonClient;

    @PostConstruct //修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行
    public void init(){
        redissonClient = Redisson.create();
    }

    public RedissonClient getRedissonClient(){
        return redissonClient;
    }

}
