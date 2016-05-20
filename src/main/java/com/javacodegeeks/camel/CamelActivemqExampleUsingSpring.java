package com.javacodegeeks.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelActivemqExampleUsingSpring {
	public static final void main(String[] args) throws Exception {
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(
				appContext, false);
		try {
			camelContext.start();
			ProducerTemplate template = camelContext.createProducerTemplate();
			for (int i = 0; i < 5; i++) {
				template.sendBody("activemq:queue:start", "body" + i);
			}
			Thread.sleep(1000);
		} finally {
			camelContext.stop();
			appContext.close();
		}
	}
}
