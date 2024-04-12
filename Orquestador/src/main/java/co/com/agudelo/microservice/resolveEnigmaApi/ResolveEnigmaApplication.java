package co.com.agudelo.microservice.resolveEnigmaApi;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "co.com.agudelo.microservice.resolveEnigmaApi", "co.com.agudelo.microservice.resolveEnigmaApi.api" , "co.com.agudelo.microservice.resolveEnigmaApi.config"})
public class ResolveEnigmaApplication {


	public static void main(String[] args) throws Exception {
        new SpringApplication(ResolveEnigmaApplication.class).run(args);
    }
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
   
}
