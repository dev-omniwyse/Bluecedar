package com.bluecedar.service.analyzer;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
 * @author Ramu Enugala
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig  extends WebMvcConfigurationSupport{

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.bluecedar.service.analyzer"))
          .paths(PathSelectors.any())     
          .build()
          .apiInfo(apiInfo())
          .globalOperationParameters(
        	        Arrays.asList(new ParameterBuilder()
        	                .name("X-Authorization")
        	                .modelRef(new ModelRef("string"))
        	                .parameterType("header")
        	                .required(true)
        	                .build()));                                           
    }
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
		.title("Bluecedar API")
		.version("Analyzer v1.0")
		.description("Bluecedar api to analyze logs and reports")
		.build();
	}
	
	@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
    
}
