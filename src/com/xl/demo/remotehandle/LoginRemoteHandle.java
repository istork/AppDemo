package com.xl.demo.remotehandle;

import java.util.HashMap;

import com.xl.demo.dataprovider.IDataProvider;
import com.xl.demo.request.DefaultRequest;
import com.xl.demo.response.RegisterResponse;

public class LoginRemoteHandle extends DefaultRemoteHandle {


	public LoginRemoteHandle(IDataProvider dataProvider,
			DefaultRequest requestParma, String jsonString, int taskId,
			HashMap<String, String> requestMap) {
		super(dataProvider, requestParma, jsonString, taskId, requestMap);
		// TODO Auto-generated constructor stub
	}

	public LoginRemoteHandle(IDataProvider dataProvider,
			DefaultRequest requestParma, String jsonString, int taskId) {
		super(dataProvider, requestParma, jsonString, taskId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setResponseModel() {
		// TODO Auto-generated method stub
		this.responseParma = new RegisterResponse();
	}
}
