package com.example.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "帳密")
public class NamePass{
	//public for demo, never do that!
	@ApiModelProperty(value = "帳號", example = "admin")
	public String username ;
	@ApiModelProperty(value = "密碼", example = "admin!")
	public String password ;
}