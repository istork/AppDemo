package com.xl.demo.dataprovider;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.xl.demo.common.TaskConstant;
import com.xl.demo.remotehandle.RegisterRemoteHandle;
import com.xl.demo.request.RegisterRequest;
import com.xl.demo.response.RegisterResponse;
import com.xl.demo.service.ILogicService;

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
			logicService.process("注册失败", 0);
		}
	}

	@Override
	public Object parserJson2Obj(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("jsonString = "+jsonString);
		String rev = "";
		String userid="";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			rev = jsonObject.getString("REV");
			userid = jsonObject.getString("userid");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new RegisterResponse(rev,userid);
	}

	public void sendRequest(int taskId, String phone,String username,String password,String cpassword) {
		RegisterRequest request = new RegisterRequest();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("machine", request.machine);
			jsonObject.put("type", request.type);
			jsonObject.put("screen", request.screen);
			jsonObject.put("mobile", phone);
			jsonObject.put("truename", username);
			jsonObject.put("password", password);
			jsonObject.put("cpassword", cpassword);
			System.out.println("jsonObject = "+jsonObject.toString());
			new RegisterRemoteHandle(this, request, jsonObject.toString(), taskId).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
