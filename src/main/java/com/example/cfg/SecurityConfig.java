package com.example.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.example.service.H2UserDetailsService;
import com.example.service.ThirdPartyAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired H2UserDetailsService userDetailsService;
	@Autowired ThirdPartyAuthenticationProvider authProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		  .csrf().disable()
		  //改為JWT認證，設定為Stateless
		  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		  .and()
		  .authorizeRequests()
		  .antMatchers("/api/book/**").authenticated()
		  .antMatchers("/api/user/**").hasRole("ADMIN")
		  .antMatchers("/api/auth/**").permitAll()
		  .anyRequest().authenticated()
		  .and()
		  //改為JWT認證，不需要表單登入認證
//		  .formLogin().permitAll().and()
//		  .logout()
//		  .and()
		  .httpBasic() ;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authProvider) ;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**").and().debug(true) ;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager() ;
	}
	
}
