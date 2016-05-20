package com.javacodegeeks.camel;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringExample {
	public static final void main(String[] args) throws Exception {
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		try {
			TestBean testBean = (TestBean) appContext.getBean("testBean");
			System.out.println(testBean.hello("Camel and Spring"));
		} finally {
			appContext.close();
		}
	}
}
