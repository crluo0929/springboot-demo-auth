package com.example.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class SwaggerConfig {

//	@Value("${swagger.header.name:x-api-key}") String headerName ;
	
//	@Bean
//	public Docket apiDocket() {
//		
//		RequestParameter parameter = new RequestParameterBuilder()
//		          .name(headerName) //要送出去的Key
//		          .description("extrakey")
//		          .in(ParameterType.HEADER) //放在header中送出
//		          .build() ;
//
//		return new Docket(DocumentationType.OAS_30)
//		  .groupName("後台API")
//		  .apiInfo(apiInfo())
//		  .select()
//		  .apis(RequestHandlerSelectors.basePackage("com.example.api"))
//		  .build()
//		  .globalRequestParameters(Collections.singletonList(parameter))
//		  .securitySchemes(securitySchemes())  //設定用哪些認證方式，如在Header上加Authorization
//          .securityContexts(securityContexts()); //設定這些認證方式對應的操作對象，如某些API
//          
//	}
//	
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.title("後台API清單")
//				.description("提供後台API")
//				.build();
//				
//	}
//	
//	private List<SecurityScheme> securitySchemes() {
//        //設定ApiKey(要帶的Key, Key的說明, 要在哪一部份帶入 ex: header or query )
//        ApiKey apiKey = new ApiKey("Authorization", "Bearer Token", In.HEADER.toValue() );
//        return Collections.singletonList(apiKey);
//    }
//    
//    private List<SecurityContext> securityContexts() {
//    AuthorizationScope[] scope = {new AuthorizationScope("global", "desc")} ;
//        return Collections.singletonList(
//                SecurityContext.builder()
//                        .securityReferences(Collections.singletonList(
//                        //設定對應ApiKey的Key
//                        new SecurityReference("Authorization", scope)))
//                        //對/api/下的所有API套用
//                        .operationSelector(s->s.requestMappingPattern().matches("/api/.*"))
//                        .build()
//        );
//    }

	
	
}
