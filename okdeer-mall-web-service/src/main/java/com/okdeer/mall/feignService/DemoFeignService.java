package com.okdeer.mall.feignService;

import com.okdeer.mall.entity.UserDemo;
import com.okdeer.mall.feignService.fallback.DemoFeignFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @FeignClient注解定义了该接口是一个Feign客户端，
 * name指定了注册到Eureka上的服务名，fallback是服务降级后的接口实现类。
 *
 * 从这里看我之前的理解是对的 之所以没有调用成功 是原来我直接远程注入manager 导致不能发现远程的manager
 * 少了这个service 远程调用的过程
 * FeignClient指定的名称是自定义 一般建议跟接口名称保持一致
 * RequestMapping里的url地址跟我们之前的请求url一样
 */
@FeignClient(name="demo-feign-freeservice",fallback=DemoFeignFallback.class)
public interface DemoFeignService{

    /**
     * @RequestMapping里指定了请求的相对url和http请求方式，与服务端一一对应。
     * 入参里的@RequestParam、@RequestBody、@RequestHeader注解比起服务端多了value属性，这里不能省略，需要显式的告知Feign客户端参数要如何对应。
     * @param name
     * @return
     */
    @RequestMapping(value="/feign-service/serviceGet",method=RequestMethod.GET)
    String helloService(@RequestParam("name") String name);

    @RequestMapping(value="/feign-service/serviceHead", method=RequestMethod.HEAD)
    String helloService(@RequestHeader("name") String name,
                        @RequestHeader("password") String password);

    @RequestMapping(value="/feign-service/servicePost", method=RequestMethod.POST)
    String helloService(@RequestBody UserDemo userDemo);

}