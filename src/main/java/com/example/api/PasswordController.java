package com.example.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class PasswordController {

	@Autowired PasswordEncoder encoder ;
	
	@GetMapping("/api/encode/{password}")
	public String encodePassword(@PathVariable String password) {
		String encodedPassword = encoder.encode(password);
		return encodedPassword ;
	}
	
	@PostMapping(path = "/api/encode/matches")
	public boolean matches(@RequestBody MatchesParam p) {
		return encoder.matches(p.rawPassword, p.encodedPassword) ;
	}

}
class MatchesParam{
	public String rawPassword ;
	public String encodedPassword ;
}