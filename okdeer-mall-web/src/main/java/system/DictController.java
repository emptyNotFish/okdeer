package system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import system.entity.DictDto;

import java.util.List;
import java.util.Map;

/**
 * 这里相当于service层的东西
 * 具体可以拓展出dao层和xml连接数据库
 * 搭建maven私服 将service打包放到maven仓库中引入
 * Created by pact on 2018/4/12.
 */
@RestController
//@RequestMapping("/dict")
@RequestMapping("/feign-service")
public class DictController {

    @Autowired
    //private DictManager dictManager;

    //服务提供方就是个简单的EurekaClient端+web应用
    @RequestMapping(value = "findDictList", method = RequestMethod.POST)
    public List<DictDto> findDictList(Map<String, Object> condition){
        return null;

    }

    @RequestMapping(value="/serviceGet",method=RequestMethod.GET)
    public String helloService(@RequestParam String name) {
        return "HelloServiceImpl name :"+name;
    }
}
