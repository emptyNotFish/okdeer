package com.okdeer.mall.controller;

import commons.CommonError;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utils.DateUtil;
import utils.JSONResult;
import utils.Md5;
import utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/21 0021.
 */
@RestController
@RequestMapping("/userLogin")
public class UserLoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    /**
     * 返回16位的code
     * @return
     */
    @RequestMapping(value = "/get/code", method = RequestMethod.POST)
    public JSONResult getCode(){
        try {
            String nowDate = DateUtil.getDateStr(DateUtil.strToDate(DateUtil.NowStr()), "yyyy-MM-dd");
            Md5 md5 = new Md5();
            String code = md5.getMD5ofStr(nowDate);
            if (StringUtils.isEmpty(code)) {
                return CommonError.FAIL_ERROR.toJSONResult("获取CODE");
            } else {
                code = StringUtils.subStringAdd(code, 16, "0");
                return JSONResult.success(code);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CommonError.FAIL_ERROR.toJSONResult("获取CODE");
        }
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }
}
