/*
 * Copyright(C) 2016-2021 okdeer.com Inc. All rights reserved
 * SysMenu.java
 * @Date 2018-04-21 Created
 * 注意：本内容仅限于友门鹿公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.okdeer.mall.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单信息表
 * 
 * @author Administrator
 * @version 1.0 2018-04-21
 */
@Data
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单编码
     */
    private String code;
    /**
     * 1一级菜单2二级3三级
     */
    private Integer level;
    /**
     * 访问url
     */
    private String url;
    /**
     * 父节点id
     */
    private String parentId;
    /**
     * 是否删除0否1是
     */
    private Boolean disabled;
    /**
     * 备注
     */
    private String remark;

}