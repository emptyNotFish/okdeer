package com.okdeer.mall.system.service;


import com.okdeer.mall.system.dto.DictDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by pact on 2018/4/2.
 */

public interface DictService {
    /**
     * 获取数据字典列表
     * @param condition
     * @return
     */
    public List<DictDto> findDictList(Map<String, Object> condition);
}
