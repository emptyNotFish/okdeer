/*
 * Copyright(C) 2016-2021 okdeer.com Inc. All rights reserved
 * SysUerMapper.java
 * @Date 2018-04-21 Created
 * 注意：本内容仅限于友门鹿公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.okdeer.mall.mapper;


import base.IBaseMapper;
import com.okdeer.mall.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserMapper extends IBaseMapper {

    SysUser findByUserName(String userName);
}