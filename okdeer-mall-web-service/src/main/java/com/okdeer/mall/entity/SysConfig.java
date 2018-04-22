/*
 * Copyright(C) 2016-2021 okdeer.com Inc. All rights reserved
 * SysConfig.java
 * @Date 2018-04-21 Created
 * 注意：本内容仅限于友门鹿公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.okdeer.mall.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统配置表
 * 
 * @author Administrator
 * @version 1.0 2018-04-21
 */
@Data
public class SysConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 配置名称
     */
    private String name;
    /**
     * 配置值
     */
    private String value;
    /**
     * 配置类型
     */
    private String type;

}