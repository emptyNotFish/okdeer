package com.okdeer.mall.controller;

import com.okdeer.mall.entity.UserDemo;
import com.okdeer.mall.service.OkdeerMallWebService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/demoFeign", method=RequestMethod.GET)
public class DemoFeignController {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OkdeerMallWebService okdeerMallWebService;

	// 使用该注解描述接口方法信息
	@ApiOperation(value="获取项目组Sonar对应的Url信息", notes="根据id获取项目组Sonar对应的Url信息")
	// 使用该注解描述方法参数信息，此处需要注意的是paramType参数，需要配置成path，否则在UI中访问接口方法时，会报错
	// paramType表示参数的类型，可选的值为"path","body","query","header","form"
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "name", value = "SonarUrl表ID", required = true, dataType = "String", paramType="path")
//	})
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String demoServiceTest() {
		LOGGER.info("===call test====");
		StringBuffer sb = new StringBuffer();
		sb.append(okdeerMallWebService.helloService("yuanyuan"));
		sb.append("\n");
		sb.append(okdeerMallWebService.helloService("yjt","xixihaha"));
		sb.append("\n");
		sb.append(okdeerMallWebService.helloService(new UserDemo("yejingtao","123456")));
		return sb.toString();

	}
}