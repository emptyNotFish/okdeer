package com.okdeer.mall.redis.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by Lenovo on 2017/11/8.
 */
@Component
public class RedisMessageConsumer {
    private static final Logger log = LoggerFactory.getLogger(RedisMessageConsumer.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisMessageProducer redisMessageProducer;

    @Async
    public void onMessage(String topic, RedisMessageProcess process, int interval) {
        ListOperations<String, Object> ops = this.redisTemplate.opsForList();
        for (;;) {
            Object item = null;
            try {
                item = ops.rightPop(topic);
            } catch (Exception e) {
                ops = this.redisTemplate.opsForList();
            }

            if (item != null) {
                RedisMessage messageContent = null;
                try {
                    messageContent = (RedisMessage) item;
                } catch (Exception e) {
                    log.error("redis message deserialize error:");
                    log.error("===================================");
                    log.error("content body is : {}", item.toString());
                    log.error("===================================");
                    e.printStackTrace();
                }
                if (messageContent != null) {
                    try {
                        process.process(messageContent.getContent());
                    } catch (Exception e) {
                        log.error("redis message process error:");
                        e.printStackTrace();
                        this.redisMessageProducer.sendMessage(topic, messageContent);
                    }
                }
            } else {
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}