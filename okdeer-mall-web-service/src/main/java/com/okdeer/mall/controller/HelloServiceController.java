package com.okdeer.mall.controller;

import com.okdeer.mall.dto.UserDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * 服务提供方就是个简单的EurekaClient端+web应用，提供以下方法
 */
@RestController
@RequestMapping("/feign-service")
public class HelloServiceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private void sleep(String methodName) {
        int sleepMinTime = new Random().nextInt(3000);
        logger.info("helloService "+methodName+" sleepMinTime: "+sleepMinTime);
        try {
            Thread.sleep(sleepMinTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/serviceGet",method= RequestMethod.GET)
    public String helloService(@RequestParam String name) {
       // sleep("get");
        return "HelloServiceImpl name :"+name;
    }
    //如果缺少了RequestHeader等注解，服务运行起来以后虽然不会报错，但是获取不到入参
    @RequestMapping(value="/serviceHead", method=RequestMethod.HEAD)
    public String helloService(@RequestHeader String name,
                               @RequestHeader String password) {
        //sleep("header");
        return "helloServiceHead name :"+name +" password:"+password;
    }

    @RequestMapping(value="/servicePost", method=RequestMethod.POST)
    public String helloService(@RequestBody UserDemo userDemo) {
        //sleep("post");
        return userDemo.toString();
    }
}