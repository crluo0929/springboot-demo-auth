package com.example.api;

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

@RestController
public class AuthController {

	@Autowired AuthenticationManager authManager ;
	@Autowired JwtService jwtService ;
	
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
		return jwtService.validateToken(token) ;
	}
	
}
class NamePass{
	public String username ;
	public String password ;
}
class JwtToken{
	public String token ;
	public JwtToken(String t) { this.token = t ;}
}