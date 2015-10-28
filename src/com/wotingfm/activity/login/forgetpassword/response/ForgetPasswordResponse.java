package com.wotingfm.activity.login.forgetpassword.response;

import com.wotingfm.activity.login.forgetpassword.model.ForgetMessage;
import com.wotingfm.main.response.DefaultResponse;


public class ForgetPasswordResponse extends DefaultResponse {
	
	
	public ForgetMessage list;

	public ForgetPasswordResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForgetPasswordResponse(ForgetMessage list) {
		// TODO Auto-generated constructor stub
		super();
		this.list=list;
	}
}
