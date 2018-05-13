package com.okdeer.mall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/5/11 0011.
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigClientApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication application = new SpringApplication(ConfigClientApplication.class);
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
