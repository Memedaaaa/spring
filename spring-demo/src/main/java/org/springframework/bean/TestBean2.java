package org.springframework.bean;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TestBean2 {
//	private static final Logger logger = LoggerFactory.getLogger(TestBean2.class);
	public void test(){
		System.out.println("test...");
	}

	@EventListener
	public void get(UserRegisteredEvent event){
		System.out.println("发送注册成功短信"+event);

	}
}
