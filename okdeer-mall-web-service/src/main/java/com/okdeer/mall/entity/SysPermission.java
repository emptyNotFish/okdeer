/*
 * Copyright(C) 2016-2021 okdeer.com Inc. All rights reserved
 * SysPermission.java
 * @Date 2018-04-21 Created
 * 注意：本内容仅限于友门鹿公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.okdeer.mall.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限信息表
 * 
 * @author Administrator
 * @version 1.0 2018-04-21
 */
@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 资源访问路径
     */
    private String url;
    /**
     * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    private String permission;
    /**
     * 资源类型0菜单1按钮
     */
    private Integer type;
    /**
     * 是否删除0否1是
     */
    private Boolean disabled;
    /**
     * 父节点id
     */
    private String parentId;
    /**
     * 资源图片路径
     */
    private String icon;
}