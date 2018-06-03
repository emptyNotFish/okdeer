package com.okdeer.mall.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Administrator on 2018/6/1 0001.
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue busQueue(){
        return new Queue("hello");
    }
}
