package com.cachedemo.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args){
		BeanFactory context=new ClassPathXmlApplicationContext("applicationContext.xml");

		TestService testService = (TestService)context.getBean("testService");
		System.out.println("1--第一次查找并创建 cache");
		testService.getAllObject();
		System.out.println("2--在 cache 中查找");
		testService.getAllObject();
		System.out.println("3--remove cache"); 
		testService.updateObject(null);
		System.out.println("4--需要重新查找并创建 cache");
		testService.getAllObject();
	}
}
