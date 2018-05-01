package com.okdeer.mall.redis.queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 使用MessageListenerAdapter对这个接收者进行代理，通过反射把消息传到receiveMessage中，
 * 也可以通过实现MessageListener接口来实现消息接受者，这里为了简单使用代理的方式。
 * Created by Administrator on 2018/5/1 0001.
 */
//@Configuration
public class RedisQueueConfig {
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("topic"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        //指定Receiver为代理接收类,接收消息方法为receiveMessage
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver() {
        return new Receiver();
    }
}
