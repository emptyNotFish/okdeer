package com.okdeer.mall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * http://localhost:8888/from/dev
 * http请求地址和资源文件映射如下:
 * /{application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 *
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 * Created by pact on 2018/3/28.
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServerApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication application = new SpringApplication(ConfigServerApplication.class);
        Environment environment = application.run(args).getEnvironment();
        LOGGER.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"),
                environment.getActiveProfiles());
    }
}
