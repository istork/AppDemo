package com.wotingfm.activity.login.login.response;

import com.wotingfm.activity.login.login.model.loginmessage;
import com.wotingfm.main.response.DefaultResponse;


public class LoginResponse extends DefaultResponse {

	public loginmessage list;

	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResponse(String REV, String userid) {
		super(REV, userid);
		// TODO Auto-generated constructor stub
	}
	public LoginResponse(loginmessage list) {
		// TODO Auto-generated constructor stub
		this.list=list;
	}
	
}
