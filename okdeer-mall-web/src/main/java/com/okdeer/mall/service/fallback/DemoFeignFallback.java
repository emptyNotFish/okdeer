package com.okdeer.mall.service.fallback;


import com.okdeer.mall.entity.UserDemo;
import com.okdeer.mall.service.DemoFeignService;
import org.springframework.stereotype.Component;

@Component
public class DemoFeignFallback implements DemoFeignService {
	@Override
	public String helloService(String name) {
		return "get error";
	}

	@Override
	public String helloService(String name,String password) {
		return "head error";
	}
	
	@Override
	public String helloService(UserDemo userDemo) {
		return "post error";
	}
}
