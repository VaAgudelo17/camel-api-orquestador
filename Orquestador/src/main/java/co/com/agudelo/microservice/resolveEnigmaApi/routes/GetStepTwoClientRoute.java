package co.com.agudelo.microservice.resolveEnigmaApi.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import co.com.agudelo.microservice.resolveEnigmaApi.model.client.ClientJsonApiBodyResponseSuccess;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


@Component
public class GetStepTwoClientRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:get-step-two")
        .routeId("getStepTwo")
        
    	.setHeader(Exchange.HTTP_METHOD, constant("POST"))
    	.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
    	
        .to("freemarker:templates/GetStepTwoClientTemplate.ftl")
        .log("${body}")
        
        .hystrix()
        .hystrixConfiguration().executionTimeoutInMilliseconds(1000).end()
        
        .to("http4://localhost:8081/v1/getOneEnigma/getStep")
    	.convertBodyTo(String.class)
    	
    	.unmarshal().json(JsonLibrary.Jackson, ClientJsonApiBodyResponseSuccess[].class)
        .process(new Processor() {
        	
    		@Override
    		public void process(Exchange exchange) throws Exception {
    			ClientJsonApiBodyResponseSuccess stepResponse = ( ClientJsonApiBodyResponseSuccess ) exchange.getIn().getBody(ClientJsonApiBodyResponseSuccess.class);
    			
    			if (stepResponse.getData().get(0).getStep().equalsIgnoreCase("2")) {
    				exchange.setProperty("Step2", "Step 2: ".concat(stepResponse.getData().get(0).getStepDescription()));
    			} else {
    				exchange.setProperty("Error", "0001");
    				exchange.setProperty("descError", "Step two is not valid");
    			}
    		}
        })
        .endHystrix()
        
        .onFallback()
        .process( new Processor() {
        	
        	@Override
        	public void process(Exchange exchange) throws Exception {
        		exchange.setProperty("Error", "0002");
        		exchange.setProperty("descError", "Error consulting the step two");
        	}
        })
        
        .end()
        .log("Response error code: ${exchangeProperty[Error]}")
        .log("Response error description: ${exchangeProperty[descError]}");
    }
}