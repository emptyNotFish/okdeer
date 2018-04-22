/*
 * Copyright(C) 2016-2021 okdeer.com Inc. All rights reserved
 * SysFunction.java
 * @Date 2018-04-21 Created
 * 注意：本内容仅限于友门鹿公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.okdeer.mall.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能信息表
 * 
 * @author Administrator
 * @version 1.0 2018-04-21
 */
@Data
public class SysFunction implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 功能名称
     */
    private String name;
    /**
     * 功能编码
     */
    private String code;
    /**
     * 是否删除0否1是
     */
    private Boolean disabled;
    /**
     * 备注
     */
    private String remark;

}