package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.DBUser;
import com.example.entity.Role;

@Service
public class H2UserDetailsService implements UserDetailsService{

	@Autowired UserService userService ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DBUser dbuser = userService.getByName(username);
		if(dbuser==null) throw new UsernameNotFoundException("帳密錯誤") ;
		List<SimpleGrantedAuthority> auths = 
				 //never not be null
				dbuser.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
				.collect(Collectors.toList());
		User user = new User(dbuser.getName(),dbuser.getPass(), auths) ;
		return user;
	}

}
