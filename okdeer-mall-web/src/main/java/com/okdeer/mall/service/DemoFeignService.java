package com.okdeer.mall.service;

import com.okdeer.mall.entity.UserDemo;
import com.okdeer.mall.service.fallback.DemoFeignFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务
 * fallback采用feign的配置方式 直接增加fallback类
 * ribbon的采用方式直接在方法上面增加 hystrix在同一个类中
 */
@FeignClient(name="okdeer-mall-web-service",fallback=DemoFeignFallback.class)
public interface DemoFeignService {
	

	@RequestMapping(value="/feign-service/serviceGet",method=RequestMethod.GET)
	String helloService(@RequestParam("name") String name);
	
	@RequestMapping(value="/feign-service/serviceHead", method=RequestMethod.HEAD)
	String helloService(@RequestHeader("name") String name,
                        @RequestHeader("password") String password);
	
	@RequestMapping(value="/feign-service/servicePost", method=RequestMethod.POST)
	String helloService(@RequestBody UserDemo userDemo);

}
