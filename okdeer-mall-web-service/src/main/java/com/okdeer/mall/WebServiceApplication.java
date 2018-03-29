package com.okdeer.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 后台具体实现项目入口
 * Created by pact on 2018/3/28.
 */
@SpringBootApplication
@EnableEurekaClient // 非eureka做注册中心用@EnableDiscoveryClient 其包含EnableEurekaClient功能，eureka做注册中心 建议用EnableEurekaClient
public class WebServiceApplication{

    public static void main(String[] args){
        SpringApplication.run(WebServiceApplication.class, args);
    }


}
