package com.wotingfm.remotehandle;

import java.util.HashMap;

import com.wotingfm.dataprovider.IDataProvider;
import com.wotingfm.request.DefaultRequest;
import com.wotingfm.response.RegisterResponse;

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
		this.responseParma = new RegisterResponse();
	}
}
