package com.okdeer.mall.service;

import com.okdeer.mall.entity.SysConfig;

/**
 * Created by Administrator on 2018/4/23 0023.
 */
public interface SysConfigService {
    SysConfig findSysConfigByCode(String mes_rsa_private_key);
}
