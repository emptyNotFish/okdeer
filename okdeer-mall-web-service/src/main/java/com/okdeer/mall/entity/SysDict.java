/*
 * Copyright(C) 2016-2021 okdeer.com Inc. All rights reserved
 * SysDict.java
 * @Date 2018-04-21 Created
 * 注意：本内容仅限于友门鹿公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.okdeer.mall.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据字典表
 * 
 * @author Administrator
 * @version 1.0 2018-04-21
 */
@Data
public class SysDict implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 字典标签
     */
    private String label;
    /**
     * 字典值
     */
    private String value;
    /**
     * 字典类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否删除0否1是
     */
    private Boolean disabled;

}