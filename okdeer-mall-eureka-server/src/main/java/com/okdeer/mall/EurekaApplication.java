package com.okdeer.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by pact on 2018/3/28.
 */
@SpringBootApplication
@EnableEureka
public class EurekaApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaApplication.class, args);
    }
}
