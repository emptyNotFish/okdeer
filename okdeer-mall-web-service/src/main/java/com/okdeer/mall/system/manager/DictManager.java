package com.okdeer.mall.system.manager;

import com.google.common.collect.Lists;
import com.okdeer.mall.system.dto.DictDto;
import com.okdeer.mall.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by pact on 2018/4/2.
 */
@RequestMapping("/dict")
public interface DictManager {

    /**
     * 获取数据字典列表
     * @param condition
     * @return
     */
    @RequestMapping(value = "findDictList", method = RequestMethod.POST)
    public List<DictDto> findDictList(Map<String, Object> condition);

}
