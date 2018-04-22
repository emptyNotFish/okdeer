package com.okdeer.mall.service;

import com.okdeer.mall.entity.SysUser;

/**
 * Created by Administrator on 2018/4/22 0022.
 */
public interface SysUserService {
    SysUser findByUsername(String username);
}
