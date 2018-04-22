/*
 * Copyright(C) 2016-2021 okdeer.com Inc. All rights reserved
 * SysUserRole.java
 * @Date 2018-04-21 Created
 * 注意：本内容仅限于友门鹿公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.okdeer.mall.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联信息表
 * 
 * @author Administrator
 * @version 1.0 2018-04-21
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;

}