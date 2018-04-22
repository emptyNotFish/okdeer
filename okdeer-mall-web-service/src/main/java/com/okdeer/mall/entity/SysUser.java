/*
 * Copyright(C) 2016-2021 okdeer.com Inc. All rights reserved
 * SysUer.java
 * @Date 2018-04-21 Created
 * 注意：本内容仅限于友门鹿公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.okdeer.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息表
 * 
 * @author Administrator
 * @version 1.0 2018-04-21
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 用户账号
     */
    private String userName;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码 加密保存
     */
    private String password;
    /**
     * 加密密码的盐
     */
    private String salt;
    /**
     * 组织id
     */
    private String orgId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户 状态
     */
    private Byte status;
    /**
     * 校验盐
     */
    private String credentialsSalt;
    /**
     * 角色列表
     */
    private List<SysRole> roleList;

    public String getCredentialsSalt(){
        // salt=username+salt
        return userName+salt;
    }
}