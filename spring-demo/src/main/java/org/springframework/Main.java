package org.springframework;

import org.springframework.bean.TestBean1;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

//@Configuration
@ComponentScan("org.springframework.bean")
public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

		/**
		 * 1.BeanFactory 是什么
		 * 	- 它是ApplicationContext的父接口
		 * 	- 实际上 IOC、DI和Bean的生命周期管理都是由它的实现类提供
		 */
		System.out.println(context);

		/**
		 * 2.BeanFactory 有啥作用
		 * 	- 表面上只能getBean
		 * 	- 实际上 IOC、DI和Bean的生命周期管理都是由它的实现类提供
		 */
		Field singletonObjects =DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
		singletonObjects.setAccessible(true);
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		ConcurrentHashMap<String,Object> map = (ConcurrentHashMap<String,Object>) singletonObjects.get(beanFactory);
		map.entrySet().stream().filter(e ->e.getKey().startsWith("test")).forEach(e->{
			System.out.println(e.getKey()+"="+e.getValue());
		});

		/**
			3. ApplicationContext 让 BeanFactory 更丰富
		 *	MessageSource
		 *	ResourcePatternResolver
		 *	EnvironmentCapable
		 *	ApplicationEventPublisher
		 */

		/*
			- 国际化 MessageSource
		 */
//		System.out.println(context.getMessage("hello", null, Locale.CHINA));
//		System.out.println(context.getMessage("hello", null, Locale.ENGLISH));
//		System.out.println(context.getMessage("hello", null, Locale.JAPAN));

		/*
			- 资源 ResourcePatternResolver
		 */
		Resource[] resources =context.getResources("classpath*:messages.properties");
		for (Resource resource:resources) {
			System.out.println(resource);
		}

		/*
			- 环境变量 EnvironmentCapable
		 */
		System.out.println(context.getEnvironment().getProperty("JAVA_HOME"));
		System.out.println(context.getEnvironment().getProperty("port"));

		/*
			- 发布事件 ApplicationEventPublisher
		 */
		context.getBean(TestBean1.class).register();


	}
}