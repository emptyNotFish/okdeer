package com.okdeer.mall;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.logging.LoggingApplicationListener;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * 启动的时候加载 获取服务的名称 端口号等基本信息
 * Created by Administrator on 2018/3/29 0029.
 */
public class LoggingListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered{

    // 服务名称
    public static final String SERVICE_NAME = "service_name";
    // 服务版本号
    public static final String SERVICE_VERSION = "service_version";
    // 服务实例名称
    public static final String SERVICE_HOST = "service_host";
    // 服务端口号
    public static final String SERVICE_PORT = "service_port";
    // 默认排序值
    public static final int DEFAULT_ORDER = LoggingApplicationListener.DEFAULT_ORDER - 1;
    //
    public static final InetUtils inetUtils = new InetUtils(new InetUtilsProperties());

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        // 读取服务的配置
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(applicationEnvironmentPreparedEvent.getEnvironment());
        Properties properties = System.getProperties();

        properties.setProperty(SERVICE_NAME, propertyResolver.getProperty("spring.application.name"));
        // 获取配置的版本号
        String version = propertyResolver.getProperty("spring.application.version");
        if (!StringUtils.hasText(version)){
            version = "1.0.0";
        }
        // 服务版本号
        properties.setProperty(SERVICE_VERSION, version);
        // 服务实例名称
        properties.setProperty(SERVICE_HOST, inetUtils.findFirstNonLoopbackAddress().getHostAddress());

        // 获取配置的端口号
        String port = propertyResolver.getProperty("server.port");
        if (!StringUtils.hasText(port)){
            port = "UNKNNOWN";
        }
        // 设置端口号
        properties.setProperty(SERVICE_PORT, port);

    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }
}
