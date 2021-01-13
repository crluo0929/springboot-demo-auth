package com.example.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${jwt.token.secret:abcdefg}") String secret  ;
	@Value("${jwt.token.validity:518400}") int JWT_TOKEN_VALIDITY ; //預設1年
	
	public String generateToken(Authentication authentication) {
		Object principal = authentication.getPrincipal() ;
		//如果是用H2UserDetailsService認證，principal會是UserDetail，如果用ThirdPartyAuthenticationProvider認證，則principal會是String
		String subject = principal instanceof User ? ((User)principal).getUsername() : (String) principal ;
		String roles = authentication.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.joining(",")) ;
		Claims claim = Jwts.claims() ;
		claim.put("userrole", roles) ;
		return doGenerateToken(claim,subject) ;
	}
	
	public boolean validateToken(String token) {
		String username = (String) getClaimFromToken(token, Claims::getSubject ) ;
		String roles = (String) getClaimFromToken(token, (claims)-> claims.get("userrole") ) ;
		//TODO: 依username查詢DB，比對roles是否有被竄改，效能問題可考慮查cache
		//先只檢查token是否已過期
		return isTokenExpired(token) ;
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getClaimFromToken(token, Claims::getExpiration);
		return expiration.after(new Date());
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody(); 
		return claimsResolver.apply(claims);
	}
	
	
	private String doGenerateToken(Claims claims, String subject) {
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, JWT_TOKEN_VALIDITY);
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setExpiration(calendar.getTime())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
}
