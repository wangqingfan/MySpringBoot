package com.wqf.learn.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminInfo {


	private int adminId;
	private String displayName;
	private String adminCode;
	private String loginName;
	private String loginPwd;
	private Date createTime;
	/**
	 * 入职时间
	 */
	private Date entryTime;
	private int adminState;
	private String telPhone;
	private String adminIDCode;
	private String email;
	private int isAgent;
	private Date lstMdfTime;
	private String accid;
	private String netEastName;
	private String token;

	private String emiToken;
	private String emiAccId;
	private String emiWorkNumber;
	private String emiPassword;
	private String emiPartPhone;
	private Integer emiState;
	private String eminewCallId;
	private Integer emiCallFlag;


}
