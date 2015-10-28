package com.wotingfm.activity.login.forgetpassword.service;

import com.wotingfm.activity.login.forgetpassword.dataprovider.ForgetProvider;
import com.wotingfm.main.IProcess.ICondition;
import com.wotingfm.main.service.DefaultService;

import android.content.Context;


public class ForgetPasswordService extends DefaultService {

	public ForgetPasswordService(Context context, ICondition condition, int taskId) {
		super(context, condition, taskId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRandomTime() {
		// TODO Auto-generated method stub
		return super.randomX;
	}

	public void sendForgetRequest(int randomX,String username,String phone ,String email ) {
		super.randomX = randomX;
		new ForgetProvider(context, this).sendRequest(taskId,phone,username,email);
	}

}
