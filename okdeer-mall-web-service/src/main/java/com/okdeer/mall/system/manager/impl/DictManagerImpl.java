package com.okdeer.mall.system.manager.impl;

import com.google.common.collect.Lists;
import com.okdeer.mall.system.dto.DictDto;
import com.okdeer.mall.system.manager.DictManager;

import java.util.List;
import java.util.Map;

/**
 * Created by pact on 2018/4/2.
 */
public class DictManagerImpl implements DictManager {

    @Override
    public List<DictDto> findDictList(Map<String, Object> condition){
        List<DictDto> dictDtoList = Lists.newArrayList();
        DictDto dto = new DictDto();
        dto.setId("234253432645");
        dictDtoList.add(dto);
        return dictDtoList;
    }

}
