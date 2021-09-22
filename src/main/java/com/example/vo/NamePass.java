package com.example.vo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title="NamePass",description = "帳密")
public class NamePass{
	//public for demo, never do that!
	@Schema(title = "帳號", defaultValue = "admin")
	public String username ;
	@Schema(title = "密碼", defaultValue = "admin!")
	public String password ;
}