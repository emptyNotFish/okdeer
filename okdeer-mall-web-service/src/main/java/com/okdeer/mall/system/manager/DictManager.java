package com.okdeer.mall.system.manager;

import com.okdeer.mall.system.dto.DictDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by pact on 2018/4/2.
 */
@FeignClient(name = "dictManager")
public interface DictManager {

    /**
     * 获取数据字典列表
     * @param condition
     * @return
     */
    //@RequestMapping里指定了请求的相对url和http请求方式，与服务端一一对应
    @RequestMapping(value = "/web-service/findDictList", method = RequestMethod.POST)
    public List<DictDto> findDictList(@RequestBody Map<String, Object> condition);

}
