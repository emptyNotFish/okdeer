package com.okdeer.mall.redis.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * convertAndSend
 * Created by Administrator on 2018/5/1 0001.
 */
@RestController
@RequestMapping("/message")
public class ReceiverControllerTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/sendRedisMsg")
    public void sendRedisMessage(String message) {
        ListOperations ops =  redisTemplate.opsForList();
        //这种方式也可以发送消息
        //ops.rightPop(null);
        stringRedisTemplate.convertAndSend("topic", message);
    }
}
