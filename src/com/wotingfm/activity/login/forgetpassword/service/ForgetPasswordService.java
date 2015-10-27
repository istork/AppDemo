package com.wotingfm.activity.login.forgetpassword.service;

import android.content.Context;

import com.wotingfm.activity.login.forgetpassword.dataprovider.ForgetProvider;
import com.wotingfm.main.IProcess.ICondition;
import com.wotingfm.main.service.DefaultService;

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

	public void sendRegisterRequest(int randomX,String username,String phone ,String email ) {
		super.randomX = randomX;
		new ForgetProvider(context, this).sendRequest(taskId,phone,username,email);
	}

}
