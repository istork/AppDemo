package com.wotingfm.service;

import android.content.Context;

import com.wotingfm.IProcess.ICondition;
import com.wotingfm.dataprovider.RegisterProvider;

public class RegisterService extends DefaultService {

	public RegisterService(Context context, ICondition condition, int taskId) {
		super(context, condition, taskId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRandomTime() {
		// TODO Auto-generated method stub
		return super.randomX;
	}

	public void sendRegisterRequest(int randomX,String phone,String username,String password ,String cpassword) {
		super.randomX = randomX;
		new RegisterProvider(context, this).sendRequest(taskId,phone,username,password,cpassword);
	}

}
