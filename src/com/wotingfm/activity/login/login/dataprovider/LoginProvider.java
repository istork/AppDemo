package com.wotingfm.activity.login.login.dataprovider;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wotingfm.activity.login.login.model.loginmessage;
import com.wotingfm.activity.login.login.response.LoginResponse;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.dataprovider.DefaultDataProvider;
import com.wotingfm.main.remotehandle.RemoteHandle;
import com.wotingfm.main.service.ILogicService;
import com.wotingfm.utils.Utils;

public class LoginProvider extends DefaultDataProvider {

	public LoginProvider(Context context, ILogicService logicService) {
		super(context, logicService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(Object object) {
		if(object!=null&&object instanceof LoginResponse){
			LoginResponse response = (LoginResponse) object;
			if(logicService.getTaskId()==TaskConstant.TASK_LOGIN){
				logicService.process(response, 0);
			}
		}else{
			logicService.process("登录失败", 0);
		}
	}

	@Override
	public Object parserJson2Obj(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("jsonString = "+jsonString);
		loginmessage list = new loginmessage();
	    Gson gson=new Gson();
	    list=gson.fromJson(jsonString, new TypeToken<loginmessage>(){}.getType());
		return new LoginResponse(list);
	}

	public void sendRequest(int taskId,String username,String password) {
		RegisterRequest request = new RegisterRequest();
		JSONObject jsonObject = new JSONObject();
		try {
		jsonObject.put("SessionId", Utils.getSessionId(context));	
		jsonObject.put("Machine", request.machine);
		jsonObject.put("MobileType", request.type);
		jsonObject.put("ScreenSize", request.screen);
		jsonObject.put("IMEI", request.imei);
		jsonObject.put("UserName",username);
		jsonObject.put("Password", password);
			new RemoteHandle(this, request, jsonObject.toString(), taskId).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
