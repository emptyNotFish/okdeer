package com.okdeer.mall.service.fallback;


import com.okdeer.mall.entity.UserDemo;
import com.okdeer.mall.service.OkdeerMallWebService;
import org.springframework.stereotype.Service;

@Service
public class OkdeerMallWebFallback implements OkdeerMallWebService {
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
