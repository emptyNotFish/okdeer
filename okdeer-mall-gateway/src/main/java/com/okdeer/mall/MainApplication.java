package com.okdeer.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by pact on 2018/3/28.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy //开启zuul
public class MainApplication {

    public final static void main(String[] args){
        SpringApplication.run(MainApplication.class, args);
    }
}
