package co.com.agudelo.microservice.resolveEnigmaApi.routes;

import org.apache.camel.builder.RouteBuilder;


public class SaveLogsClientRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("seda:save-log")
		.routeId("SaveLog")
		.delay(3000)
		.log("Success transaction Log ${body}");
	}
}
