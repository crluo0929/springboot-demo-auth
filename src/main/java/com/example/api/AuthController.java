package com.example.api;

import javax.annotation.PreDestroy;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.JwtService;
import com.example.vo.JwtToken;
import com.example.vo.NamePass;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Tag(name = "認證相關API")
@Slf4j
public class AuthController {

//	private static final Logger log = LoggerFactory.getLogger(AuthController.class) ;

	@Autowired AuthenticationManager authManager ;
	@Autowired JwtService jwtService ;
	
	@Operation(summary = "API登入認證", description="登入取得Token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "登入成功，取得Token"),
			@ApiResponse(responseCode = "500", description = "登入失敗，帳密有誤")
	})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			description = "輸入帳密",
			content = @Content(schema = @Schema(allOf = NamePass.class) ) 
	)
	@PostMapping("/api/auth")
	public JwtToken auth(@RequestBody NamePass userInfo) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userInfo.username, userInfo.password) ;
		try {
			//1.進行認證
			Authentication authToken = authManager.authenticate(authentication) ;
			//2.認證成功存入SecurityContext
			SecurityContextHolder.getContext().setAuthentication(authToken);
			//3.產生JWT回傳
			String strToken = jwtService.generateToken(authToken) ;
			return new JwtToken(strToken) ;
		}catch(Exception e) {
			throw new UsernameNotFoundException("認證失敗") ;
		}
	}
	
	@PostMapping("/api/auth/validate")
	public boolean validateToken(@RequestBody String token) {
		log.debug("AuthController debug message");
		log.info("AuthController info message");
		log.warn("AuthController warn message");
		log.error("AuthController error message");
		return jwtService.validateToken(token) ;
	}
	
}