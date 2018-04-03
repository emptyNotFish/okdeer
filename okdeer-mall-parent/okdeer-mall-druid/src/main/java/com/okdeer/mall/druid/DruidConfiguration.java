package com.okdeer.mall.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import utils.AESUtils;
import utils.Md5;
import utils.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库连接池配置管理
 * Created by pact on 2018/3/27.
 */
@Configuration
public class DruidConfiguration {
    // 记录日志
    private static final Logger LOGGER = LoggerFactory.getLogger(DruidConfiguration.class);
    // 数据库配置的属性的前缀 在具体的service项目里面
    private static final String DB_PREFIX = "spring.datasource";

    /**
     * 注册一个druid内置的statviewservlet，用于展示druid的统计信息
     * statviewservlet用于
     * 提供监控信息展示的html页面
     * 提供监控信息的json api
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        LOGGER.debug("Init Druid Servlet Configuration ");
        // 开启druid的监控功能，可以在运行中通过监控数据调整程序设计，优化数据库的访问性能
        // 如果导入2.0 org.springframework.boot.context.embedded.ServletRegistrationBean 1.5.1是web.servlet.ServletRegistrationBean
        // ideal检查不允许  ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 设置IP白名单 没有或者配置为空 则允许所有访问
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单(共同存在时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * web关联监控配置
     * 注册一个FilterRegistrationBean 添加请求过滤规则
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        // webstatfilter用于采集web-jdbc关联监控的数据
        // ideal检查不允许  FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则 过滤所有请求
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    //解决 spring.datasource.filters=stat,wall,log4j 无法正常注册进去
    // 获取前缀为spring.datasource的配置
    @ConfigurationProperties(prefix = DB_PREFIX)
    @Data
    class IDataSourceProperties {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
        private int initialSize;
        private int minIdle;
        private int maxActive;
        private int maxWait;
        private int timeBetweenEvictionRunsMillis;
        private int minEvictableIdleTimeMillis;
        private String validationQuery;
        private boolean testWhileIdle;
        private boolean testOnBorrow;
        private boolean testOnReturn;
        private boolean poolPreparedStatements;
        private int maxPoolPreparedStatementPerConnectionSize;
        private String filters;
        private String connectionProperties;
        List<String> connectionInitSqls = new ArrayList<>();
        /*声明其为Bean实例
        在同样的DataSource中，首先使用被标注的DataSource*/
        @Bean
        @Primary  // 存在多个数据源时 需要指定一个数据源为主数据源
        public DataSource dataSource() {
            DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(url);

            Md5 md5 = new Md5();
            // 获取加密code 配置的账号密码都是加密后的账号密码 所以读取后需要解密
            String code = md5.getMD5ofStr("okdeer-mall-web");
            String key = StringUtils.subStringAdd(code, 16, "0");
            // 对配置的数据库账号进行AES对称解密
            String uname = StringUtils.trim(AESUtils.decryptData(username, key, key));
            // 对配置的数据库密码进行AES对称解密
            String pass = StringUtils.trim(AESUtils.decryptData(password, key, key));


            datasource.setUsername(uname);
            datasource.setPassword(pass);
            datasource.setDriverClassName(driverClassName);

            //configuration
            datasource.setConnectionInitSqls(connectionInitSqls);
            datasource.setInitialSize(initialSize);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            datasource.setMaxWait(maxWait);
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setValidationQuery(validationQuery);
            datasource.setTestWhileIdle(testWhileIdle);
            datasource.setTestOnBorrow(testOnBorrow);
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
            try {
                datasource.setFilters(filters);
            } catch (SQLException e) {
                System.err.println("druid configuration initialization filter: " + e);
            }
            datasource.setConnectionProperties(connectionProperties);
            return datasource;
        }

    }

}
