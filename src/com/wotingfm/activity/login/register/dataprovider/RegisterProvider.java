package com.wotingfm.activity.login.register.dataprovider;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wotingfm.activity.login.register.model.UserInfo;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.activity.login.register.response.RegisterResponse;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.dataprovider.DefaultDataProvider;
import com.wotingfm.main.remotehandle.RemoteHandle;
import com.wotingfm.main.service.ILogicService;
import com.wotingfm.utils.Utils;

public class RegisterProvider extends DefaultDataProvider {

	public RegisterProvider(Context context, ILogicService logicService) {
		super(context, logicService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(Object object) {
		if(object!=null&&object instanceof RegisterResponse){
			RegisterResponse response = (RegisterResponse) object;
			if(logicService.getTaskId()==TaskConstant.TASK_REGISTER){
				logicService.process(response, 0);
			}
		}else{
			logicService.process(object, 0);
		}
	}

	@Override
	public Object parserJson2Obj(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("jsonString = "+jsonString);
		UserInfo list = new UserInfo();
	    Gson gson=new Gson();
	    list=gson.fromJson(jsonString, new TypeToken<UserInfo>(){}.getType());
		return new RegisterResponse(list);
	}


	public void sendRequest(int taskId,String username,String password) {
		RegisterRequest request = new RegisterRequest();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("SessionId", Utils.getSessionId(context));
			jsonObject.put("GPS", "111");
			jsonObject.put("ScreenSize", request.screen);
			jsonObject.put("IMEI", request.imei);
			jsonObject.put("UserName", username);
			jsonObject.put("Password", password);
			jsonObject.put("MobileType", request.type);
			jsonObject.put("Machine", request.machine);
			System.out.println("jsonObject = "+jsonObject.toString());
			Log.e("zhuce", jsonObject.toString()+"");
			new RemoteHandle(this, request, jsonObject.toString(), taskId).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
