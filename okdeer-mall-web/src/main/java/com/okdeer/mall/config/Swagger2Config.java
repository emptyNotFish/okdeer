package com.okdeer.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 该配置类需要在Application同级目录下创建，在项目启动的时候，就初始化该配置类
 *
 * Created by Administrator on 2018/5/14 0014.
 */
@Configuration
@EnableSwagger2 //启动swagger2
public class Swagger2Config {

    /**
     * 创建api基本信息
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                扫描该包下的所有需要在Swagger中展示的api，@ApiIgnore注解标注的除外
                .apis(RequestHandlerSelectors.basePackage("com.okdeer.mall.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring boot 中使用swagger2构建restful apis") //api标题
                .description("测试提供restful apis") // api描述
                .contact("xuzq@") // 联系人
                .version("V1.0") // 版本号
                .build();
    }
}
