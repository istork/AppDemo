package com.wotingfm.activity.login.register.response;


import com.wotingfm.activity.login.login.model.UserInfo;

public class RegisterResponse extends RegisterDefaultResponse {

	public UserInfo list;

	public RegisterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterResponse(UserInfo list) {
		// TODO Auto-generated constructor stub
		super();
		this.list=list;
	}


	
}
