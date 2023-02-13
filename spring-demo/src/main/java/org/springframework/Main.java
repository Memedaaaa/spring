package org.springframework;

import org.springframework.bean.TestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//@Configuration
@ComponentScan("org.springframework.bean")
public class Main {
	public static void main(String[] args) {
		ApplicationContext application = new AnnotationConfigApplicationContext(Main.class);
		TestBean testBean = application.getBean(TestBean.class);
		testBean.test();
	}
}