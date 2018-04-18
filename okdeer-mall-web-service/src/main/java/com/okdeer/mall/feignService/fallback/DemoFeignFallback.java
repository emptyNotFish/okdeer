package com.okdeer.mall.feignService.fallback;

import com.okdeer.mall.entity.UserDemo;
import com.okdeer.mall.feignService.DemoFeignService;
import org.springframework.stereotype.Component;

@Component
public class DemoFeignFallback implements DemoFeignService {
    /**
     * 发现这里的入参里我故意去掉了@RequestParam、@RequestBody、@RequestHeader注解，
     * 因为这几个注解本质上的意义就在于Feign在做微服务调用的时候对http传递参数用的，但服务降级根本不会做http请求了，所以此处可以省略。
     * @param name
     * @return
     */
    @Override
    public String helloService(String name) {
        return "get error";
    }

    @Override
    public String helloService(String name,String password) {
        return "head error";
    }

    @Override
    public String helloService(UserDemo userDemo) {
        return "post error";
    }
}