package com.example.vo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title="JwtToken",description = "Token")
public class JwtToken{
	//public for demo, never do that!
	@Schema(description = "token")
	public String token ;
	public JwtToken(String t) { this.token = t ;}
}
