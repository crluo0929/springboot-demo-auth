package com.example.cfg;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableOpenApi 引入即有AutoConfigure功能
public class SwaggerConfig {

	@Value("${swagger.header.name:x-api-key}") String headerName ;
	
	@Bean
	public Docket apiDocket() {
		
		RequestParameter parameter = new RequestParameterBuilder()
		          .name(headerName) //要送出去的Key
		          .description("extrakey")
		          .in(ParameterType.HEADER) //放在header中送出
		          .build() ;

		return new Docket(DocumentationType.OAS_30)
		  .groupName("後台API")
		  .apiInfo(apiInfo())
		  .select()
		  .apis(RequestHandlerSelectors.basePackage("com.example.api"))
		  .build()
		  .globalRequestParameters(Collections.singletonList(parameter))
		  .securitySchemes(securitySchemes())  //設定用哪些認證方式，如在Header上加Authorization
          .securityContexts(securityContexts()); //設定這些認證方式對應的操作對象，如某些API
          
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("後台API清單")
				.description("提供後台API")
				.build();
				
	}
	
	private List<SecurityScheme> securitySchemes() {
        //設定ApiKey(要帶的Key, Key的說明, 要在哪一部份帶入 ex: header or query )
        ApiKey apiKey = new ApiKey("Authorization", "Bearer Token", In.HEADER.toValue() );
        return Collections.singletonList(apiKey);
    }
    
    private List<SecurityContext> securityContexts() {
    AuthorizationScope[] scope = {new AuthorizationScope("global", "desc")} ;
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(
                        //設定對應ApiKey的Key
                        new SecurityReference("Authorization", scope)))
                        //對/api/下的所有API套用
                        .operationSelector(s->s.requestMappingPattern().matches("/api/.*"))
                        .build()
        );
    }

	
	
}
