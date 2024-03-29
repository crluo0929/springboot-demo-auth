package com.example.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.media.Schema;

@RestControllerAdvice(basePackages={"com.example.api"})
public class RestExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResultMessage errorHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) throws Exception {
		int httpStatus = 500;
		int errorcode = 0 ; //get from specific BusinessException
		String errorMessage = e.getMessage() ; //get from specific BussinessException
		resp.setStatus(httpStatus);
		return new ResultMessage(httpStatus,errorcode,errorMessage);
	}
	
}

@Schema(title="ResultMessage", description="結果資訊")
class ResultMessage {
	@Schema(description="http回傳狀態碼")
	private int httpStatus;
	@Schema(description="錯誤碼")
	private int errorcode ;
	@Schema(description="訊息")
	private String message ;
	
	public ResultMessage(int status,int errorcode,String message) {
		this.httpStatus=status;
		this.errorcode = errorcode ;
		this.message = message ;
	}
	
	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}