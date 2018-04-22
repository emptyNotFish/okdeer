/*
 * Copyright(C) 2016-2021 okdeer.com Inc. All rights reserved
 * SysDictMapper.java
 * @Date 2018-04-21 Created
 * 注意：本内容仅限于友门鹿公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.okdeer.mall.mapper;


import base.IBaseMapper;
import com.okdeer.mall.entity.SysDict;

import java.util.List;
import java.util.Map;

public interface SysDictMapper extends IBaseMapper {
    /**
     *
     * @param id
     * @return
     */
    public SysDict findById(String id);

    /**
     * 删除字典
     * @param id
     */
    public void delete(String id);
    /**
     *
     * @param condition
     * @return
     */
    public List<SysDict> findDictsByType(Map<String, Object> condition);

    /**
     *
     * @param condition
     * @return
     */
    public SysDict findDictByTypeAndValue(Map<String, Object> condition);


}