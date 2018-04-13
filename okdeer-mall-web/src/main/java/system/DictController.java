package system;

import com.okdeer.mall.system.dto.DictDto;
import com.okdeer.mall.system.entity.Dict;
import com.okdeer.mall.system.manager.DictManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 这里的service项目应该通过单独的jar包引入 而不是在父项目里面继承引入
 * 而且在设计项目中 最好不要所有的项目都继承一个父项目
 * 这样会增加耦合
 * 搭建maven私服 将service打包放到maven仓库中引入
 * Created by pact on 2018/4/12.
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictManager dictManager;


    @RequestMapping(value = "findDictList", method = RequestMethod.POST)
    public List<DictDto> findDictList(Map<String, Object> condition){
        return dictManager.findDictList(condition);

    }
}
