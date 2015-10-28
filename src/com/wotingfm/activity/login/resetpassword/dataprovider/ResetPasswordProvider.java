package com.wotingfm.activity.login.resetpassword.dataprovider;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.activity.login.resetpassword.model.UserInfo;
import com.wotingfm.activity.login.resetpassword.response.ResetPassWordResponse;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.dataprovider.DefaultDataProvider;
import com.wotingfm.main.remotehandle.RemoteHandle;
import com.wotingfm.main.service.ILogicService;

public class ResetPasswordProvider extends DefaultDataProvider{
	
	public ResetPasswordProvider(Context context, ILogicService logicService) {
		super(context, logicService);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void process(Object object) {
		if(object!=null&&object instanceof ResetPassWordResponse){
			ResetPassWordResponse response = (ResetPassWordResponse) object;
			if(logicService.getTaskId()==TaskConstant.Task_Reset){
				logicService.process(response, 0);
			}
		}else{
			logicService.process("注册失败", 0);
		}
	}
	@Override
	public Object parserJson2Obj(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("jsonString = "+jsonString);
		UserInfo list = new UserInfo();
	    Gson gson=new Gson();
	    list=gson.fromJson(jsonString, new TypeToken<UserInfo>(){}.getType());
		return new ResetPassWordResponse(list);
	}
	public void sendRequest(int taskId, String password) {
		RegisterRequest request = new RegisterRequest();
		JSONObject jsonObject = new JSONObject();
		try {
		/*	jsonObject.put("machine", request.machine);
			jsonObject.put("type", request.type);
			jsonObject.put("screen", request.screen);
			jsonObject.put("mobile", phone);*/
			jsonObject.put("password", password);
		/*	jsonObject.put("cpassword", cpassword);*/
			System.out.println("jsonObject = "+jsonObject.toString());
			new RemoteHandle(this, request, jsonObject.toString(), taskId).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
