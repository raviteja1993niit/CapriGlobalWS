package com.cgcl.config;

import java.util.Vector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors
				.basePackage("com.cgcl.controller"))
				.paths(PathSelectors.any()).build().apiInfo(metaData());
	}
	 private ApiInfo metaData() {
		 ApiInfo apiInfo = new ApiInfo(
               "REST API for Capri Global Capital Limited",
               "Developed by: RAVI TEJA",
               "PRIME360-V1.2",
               "Terms of service",
               new Contact("Posidex Technologies", "https://www.posidex.com/", "info@posidex.com"),
               "Prime360 License Version 1.1",
               "https://www.posidex.com/", new Vector<>());
       return apiInfo;
	    }
}
