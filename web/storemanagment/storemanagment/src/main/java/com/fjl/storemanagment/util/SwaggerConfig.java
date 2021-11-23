package com.fjl.storemanagment.util;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build().apiInfo(getApiInformation());
	}
	
	private ApiInfo getApiInformation() {
        return new ApiInfo("Store Managment",
        		"Servicio API RESTful de consulta sobre ventas.",
        		"1.0","http://swagger.io/terms/", 
        		new Contact("Facundo López","","facundojlopez@gmail.com"),
        		"LICENSE",
        		"LICENSE URL",
        		Collections.emptyList());
               
                
    }

}
