package com.wotingfm.activity.login.forgetpassword.dataprovider;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wotingfm.activity.login.forgetpassword.model.ForgetMessage;
import com.wotingfm.activity.login.forgetpassword.response.ForgetPasswordResponse;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.dataprovider.DefaultDataProvider;
import com.wotingfm.main.remotehandle.RemoteHandle;
import com.wotingfm.main.service.ILogicService;

public class ForgetProvider extends DefaultDataProvider {

	public ForgetProvider(Context context, ILogicService logicService) {
		super(context, logicService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(Object object) {
		if(object!=null&&object instanceof ForgetPasswordResponse){
			ForgetPasswordResponse response = (ForgetPasswordResponse) object;
			if(logicService.getTaskId()==TaskConstant.Task_Forget){
				logicService.process(response, 0);
			}
		}else{
			logicService.process("注册失败", 0);
		}
	}

	@Override
	public Object parserJson2Obj(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("jsonString = " + jsonString);
		ForgetMessage list = new ForgetMessage();
		Gson gson = new Gson();
		list = gson.fromJson(jsonString, new TypeToken<ForgetMessage>() {
		}.getType());
		return new ForgetPasswordResponse(list);
	}

	public void sendRequest(int taskId, String username,String phone ,String email) {
		RegisterRequest request = new RegisterRequest();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("machine", request.machine);
			jsonObject.put("type", request.type);
			jsonObject.put("screen", request.screen);
			jsonObject.put("mobile", phone);
			jsonObject.put("truename", username);
			jsonObject.put("email", email);
			System.out.println("jsonObject = "+jsonObject.toString());
			new RemoteHandle(this, request, jsonObject.toString(), taskId).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
