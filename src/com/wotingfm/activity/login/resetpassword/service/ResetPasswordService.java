package com.wotingfm.activity.login.resetpassword.service;

import com.wotingfm.activity.login.resetpassword.dataprovider.ResetPasswordProvider;
import com.wotingfm.main.IProcess.ICondition;
import com.wotingfm.main.service.DefaultService;

import android.content.Context;


public class ResetPasswordService extends DefaultService{
	public ResetPasswordService(Context context, ICondition condition, int taskId) {
		super(context, condition, taskId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRandomTime() {
		// TODO Auto-generated method stub
		return super.randomX;
	}

	public void sendResetPasswordRequest(int randomX,String password) {
		super.randomX = randomX;
		new ResetPasswordProvider(context, this).sendRequest(taskId,password);
	}
}
