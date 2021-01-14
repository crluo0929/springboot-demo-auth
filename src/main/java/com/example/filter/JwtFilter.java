package com.example.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.example.service.JwtService;

import io.jsonwebtoken.Claims;

@Component
public class JwtFilter extends GenericFilterBean{

	@Value("${jwt.token.HeaderKey:Authorization}") String XAUTH_TOKEN_HEADER_NAME  ; //default Authorization
	@Autowired JwtService jwtService ;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		//1.取出token
		String header = httpServletRequest.getHeader(XAUTH_TOKEN_HEADER_NAME);
		if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			try {
				String token = header.substring(7);
				//JWT Authorization的認證格式為 "Bearer token"
				//2.驗證token
				boolean isValid = jwtService.validateToken(token) ;
				if(isValid) {
					//3.存入SecurityContextHolder，供同一Thread後續可以取得current user
					String username = jwtService.getClaimFromToken(token, Claims::getSubject ) ;
					String roles = (String) jwtService.getClaimFromToken(token, (claim)-> claim.get("userrole") ) ;
					List<SimpleGrantedAuthority> list = Arrays
							.asList(roles.split(","))
							.stream()
							.map(role-> new SimpleGrantedAuthority(role))
							.collect(Collectors.toList()) ;
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,null,list);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}catch(Exception e) {

			}
		}
		chain.doFilter(request, response);
		
		
	}

}
