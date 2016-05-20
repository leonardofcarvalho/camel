package com.javacodegeeks.camel.routes;

import org.apache.camel.builder.RouteBuilder;

public class ActiveMqRouteBuilder extends RouteBuilder {
	public void configure() {
		from("activemq:queue:start").to("bean:testBean?method=hello").to(
				"stream:out");
	}
}
