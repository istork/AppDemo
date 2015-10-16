package com.xl.demo.dataprovider;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.xl.demo.common.TaskConstant;
import com.xl.demo.remotehandle.LoginRemoteHandle;
import com.xl.demo.request.RegisterRequest;
import com.xl.demo.response.LoginResponse;
import com.xl.demo.service.ILogicService;

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
		String rev="";
		String userid="";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			rev = jsonObject.getString("REV");
			userid = jsonObject.getString("userid");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new LoginResponse(rev, userid);
	}

	public void sendRequest(int taskId,String username,String password) {
		RegisterRequest request = new RegisterRequest();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("machine", request.machine);
			jsonObject.put("type", request.type);
			jsonObject.put("screen", request.screen);
			jsonObject.put("mobile", username);
			jsonObject.put("password", password);
			new LoginRemoteHandle(this, request, jsonObject.toString(), taskId).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
