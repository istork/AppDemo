package com.wotingfm.service;

import com.wotingfm.IProcess.ICondition;
import com.wotingfm.dataprovider.GoodsProvider;
import com.wotingfm.dataprovider.LoginProvider;

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
