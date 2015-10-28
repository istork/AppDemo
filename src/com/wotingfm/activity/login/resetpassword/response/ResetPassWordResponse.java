package com.wotingfm.activity.login.resetpassword.response;

import com.wotingfm.activity.login.resetpassword.model.UserInfo;
import com.wotingfm.main.response.DefaultResponse;


public class ResetPassWordResponse extends DefaultResponse {

	public UserInfo list;

	public ResetPassWordResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResetPassWordResponse(UserInfo list) {
		// TODO Auto-generated constructor stub
		super();
		this.list=list;
	}

}
