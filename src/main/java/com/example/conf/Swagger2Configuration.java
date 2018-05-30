package com.example.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
	
	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(buildApiInf())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.controller"))
				.paths(PathSelectors.any()).build();
		
	}

	private ApiInfo buildApiInf() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("swagger2practice")
				.description("mySpringMvc接口文档")
				.termsOfServiceUrl("https://github.com/X-smart/MySpringMVC")
				.contact(new Contact("xuconghui", "https://github.com/X-smart/MySpringMVC", "xchaigl@gmail.com"))
				.build();
	}
	
}
