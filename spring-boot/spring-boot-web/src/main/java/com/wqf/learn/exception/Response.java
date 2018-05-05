package com.wqf.learn.exception;

import java.io.Serializable;

import lombok.Data;

@Data
public class Response implements Serializable{

	private String code;
	
	private String msg;
}
