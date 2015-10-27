package com.wotingfm.main.remotehandle;

import java.util.HashMap;

import com.wotingfm.activity.login.register.response.RegisterResponse;
import com.wotingfm.main.dataprovider.IDataProvider;
import com.wotingfm.main.request.DefaultRequest;

public class RemoteHandle extends DefaultRemoteHandle {


	public RemoteHandle(IDataProvider dataProvider,
			DefaultRequest requestParma, String jsonString, int taskId,
			HashMap<String, String> requestMap) {
		super(dataProvider, requestParma, jsonString, taskId, requestMap);
		// TODO Auto-generated constructor stub
	}

	public RemoteHandle(IDataProvider dataProvider,
			DefaultRequest requestParma, String jsonString, int taskId) {
		super(dataProvider, requestParma, jsonString, taskId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setResponseModel() {
		// TODO Auto-generated method stub
	}
}
