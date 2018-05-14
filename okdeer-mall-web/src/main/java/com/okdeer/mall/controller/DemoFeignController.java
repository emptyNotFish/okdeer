package com.okdeer.mall.controller;

import com.okdeer.mall.entity.UserDemo;
import com.okdeer.mall.service.DemoFeignService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/demoFeign", method=RequestMethod.GET)
public class DemoFeignController {

	@Autowired
	private DemoFeignService demoFeignService;

	// 使用该注解描述接口方法信息
	@ApiOperation(value="获取项目组Sonar对应的Url信息", notes="根据id获取项目组Sonar对应的Url信息")
	// 使用该注解描述方法参数信息，此处需要注意的是paramType参数，需要配置成path，否则在UI中访问接口方法时，会报错
	// paramType表示参数的类型，可选的值为"path","body","query","header","form"
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "name", value = "SonarUrl表ID", required = true, dataType = "String", paramType="path")
//	})
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String demoServiceTest() {
		StringBuffer sb = new StringBuffer();
		sb.append(demoFeignService.helloService("yuanyuan"));
		sb.append("\n");
		sb.append(demoFeignService.helloService("yjt","xixihaha"));
		sb.append("\n");
		sb.append(demoFeignService.helloService(new UserDemo("yejingtao","123456")));
		return sb.toString();

	}
}