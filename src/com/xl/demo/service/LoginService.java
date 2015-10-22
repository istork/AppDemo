package com.xl.demo.service;

import com.xl.demo.IProcess.ICondition;
import com.xl.demo.dataprovider.GoodsProvider;
import com.xl.demo.dataprovider.LoginProvider;

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
