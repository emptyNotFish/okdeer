package com.okdeer.mall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 后台具体实现项目入口
 * Created by pact on 2018/3/28.
 */
@SpringBootApplication
//@EnableEurekaClient // 非eureka做注册中心用@EnableDiscoveryClient 其包含EnableEurekaClient功能，eureka做注册中心 建议用EnableEurekaClient
public class WebServiceApplication{

    private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(WebServiceApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOGGER.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());

    }


}
