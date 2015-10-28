package com.wotingfm.activity.login.login.service;


import com.wotingfm.activity.login.login.dataprovider.LoginProvider;
import com.wotingfm.main.IProcess.ICondition;
import com.wotingfm.main.service.DefaultService;

import android.content.Context;

public class LoginService extends DefaultService {

	public LoginService(Context context, ICondition condition, int taskId) {
		super(context, condition, taskId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRandomTime() {
		// TODO Auto-generated method stub
		return super.randomX;
	}

	public void sendRequest(int randomX,String username,String password) {
		super.randomX = randomX;
		new LoginProvider(context, this).sendRequest(taskId, username, password);
	}

}
