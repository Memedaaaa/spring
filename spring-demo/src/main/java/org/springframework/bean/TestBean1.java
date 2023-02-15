package org.springframework.bean;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TestBean1 {

//	private static final Logger logger = LoggerFactory.getLogger(TestBean1.class);
    @Autowired
	private ApplicationEventPublisher context;
	public void test(){
		System.out.println("test...");
	}

	public void register(){
		System.out.println("注册");
		context.publishEvent(new UserRegisteredEvent(this));
	}
}
