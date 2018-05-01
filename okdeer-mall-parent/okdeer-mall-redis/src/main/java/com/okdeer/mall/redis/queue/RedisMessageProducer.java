package com.okdeer.mall.redis.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Lenovo on 2017/11/8.
 */

@Component
public class RedisMessageProducer {
    @Autowired
    private RedisTemplate redisTemplate;

    public void sendMessage(String topic, Object value) {
        ListOperations<String, Object> ops = this.redisTemplate.opsForList();
        if (value == null) {
            return;
        }
        RedisMessage msg = new RedisMessage();
        msg.setId(Thread.currentThread().getName());
        msg.setContent(value);
        ops.leftPush(topic, msg);
    }
}
