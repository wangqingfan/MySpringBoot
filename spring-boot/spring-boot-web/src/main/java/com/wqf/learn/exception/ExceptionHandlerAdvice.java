package com.wqf.learn.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ResponseBody
	@ExceptionHandler(value={NullPointerException.class})
	public Response nullPointException(NullPointerException exception){
		Response res = new Response();
		res.setCode("404");
		res.setMsg("空指针");
		return res;
	}
}
