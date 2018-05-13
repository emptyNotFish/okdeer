package com.okdeer.mall.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/5/12 0012.
 */
@RestController
public class ConfigTestController {

    @Value("${from}")
    String from;

    @RequestMapping(value = "/from")
    public String hi(){
        return from;
    }
}
