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

@RestController
public class AuthController {

	@Autowired AuthenticationManager authManager ;
	
	@PostMapping("/api/auth")
	public String auth(@RequestBody NamePass userInfo) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userInfo.username, userInfo.password) ;
		//1.進行認證
		try {
			Authentication authToken = authManager.authenticate(authentication) ;
			//2.認證成功存入SecurityContext
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}catch(Exception e) {
			throw new UsernameNotFoundException("認證失敗") ;
		}
		//3.產生JWT回傳
		
		return "" ;
	}
	
}
class NamePass{
	public String username ;
	public String password ;
}