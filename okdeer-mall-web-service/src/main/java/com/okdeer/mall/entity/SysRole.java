/*
 * Copyright(C) 2016-2021 okdeer.com Inc. All rights reserved
 * SysRole.java
 * @Date 2018-04-21 Created
 * 注意：本内容仅限于友门鹿公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.okdeer.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色信息表
 * 
 * @author Administrator
 * @version 1.0 2018-04-21
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态 0不可用1可用
     */
    private Boolean status;

    private List<SysPermission> permissionList;

}