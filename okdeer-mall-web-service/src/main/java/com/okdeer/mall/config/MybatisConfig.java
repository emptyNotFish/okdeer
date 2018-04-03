package com.okdeer.mall.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 注册mybatis分页插件pagehelper
 * Created by Administrator on 2018/4/3 0003.
 */
//@Configuration
public class MybatisConfig {

   // @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        //添加配置 也可以指定文件路径
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
