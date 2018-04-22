package com.okdeer.mall.service.impl;

import com.google.common.collect.Sets;
import com.okdeer.mall.entity.*;
import com.okdeer.mall.mapper.*;
import com.okdeer.mall.service.SysUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/22 0022.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public SysUser findByUsername(String username) {
        //获取用户的角色和权限列表
        // 简单一点不用关联查询
        //根据用户名查询用户信息
        SysUser sysUser = sysUserMapper.findByUserName(username);
        // 根据userid查询管理的角色信息
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getId());
        List<SysRole> sysRoleList = sysRoleMapper.findRoleList(sysUserRole);
        if (CollectionUtils.isNotEmpty(sysRoleList)) {
            sysUser.setRoleList(sysRoleList);
            Set<SysPermission> sysPermissionSet = Sets.newHashSet();
            for (SysRole sysRole : sysRoleList){
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setRoleId(sysRole.getId());
                List<SysPermission> sysPermissionList = sysPermissionMapper.findPermissionList(sysRolePermission);
                sysRole.setPermissionList(sysPermissionList);
            }
        }
        //根据角色id查询权限信息
        return sysUser;
    }
}
