package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.DBUser;
import com.example.entity.Role;

@Service
public class ThirdPartyAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal() ;
		String password = (String) authentication.getCredentials() ;
		List<Role> roles = authenticationFrom3Party(username,password) ;
		List<SimpleGrantedAuthority> auths = 
				roles.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
				.collect(Collectors.toList());
		return new UsernamePasswordAuthenticationToken(username, password, auths);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication == UsernamePasswordAuthenticationToken.class ;
	}
	
	@Autowired UserService userService ;
	@Autowired PasswordEncoder encoder ;
	
	
	private List<Role> authenticationFrom3Party(String username,String password){
		//模擬假裝呼叫第三方API認證取得角色資訊，實際上去查H2 database
		DBUser dbuser = userService.getByName(username);
		boolean match = encoder.matches(password, dbuser.getPass()) ;
		if(match) {
			return dbuser.getRoles() ;
		}else {
			throw new UsernameNotFoundException("帳密錯誤") ;
		}
	}

}
