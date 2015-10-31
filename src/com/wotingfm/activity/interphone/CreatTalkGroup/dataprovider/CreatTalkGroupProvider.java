package com.wotingfm.activity.interphone.CreatTalkGroup.dataprovider;


import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wotingfm.activity.interphone.CreatTalkGroup.model.CreatTalkGroup;
import com.wotingfm.activity.interphone.CreatTalkGroup.response.CreatTalkGroupResponse;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.dataprovider.DefaultDataProvider;
import com.wotingfm.main.remotehandle.RemoteHandle;
import com.wotingfm.main.service.ILogicService;

public class CreatTalkGroupProvider extends DefaultDataProvider {

	public CreatTalkGroupProvider(Context context, ILogicService logicService) {
		super(context, logicService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(Object object) {
		if(object!=null&&object instanceof CreatTalkGroupResponse){
			CreatTalkGroupResponse response = (CreatTalkGroupResponse) object;
			if(logicService.getTaskId()==TaskConstant.Task_CreatTalkGroup){
				logicService.process(response, 0);
			}
		}else{
			logicService.process("联网失败", 0);
		}
	}

	@Override
	public Object parserJson2Obj(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("jsonString = "+jsonString);
		
//		List<TalkPerson> list = new ArrayList<TalkPerson>();
//		Gson gson=new Gson();
//		list=gson.fromJson(jsonString, new TypeToken<List<TalkPerson>>(){}.getType());
		
		
		CreatTalkGroup list = new CreatTalkGroup();
	    Gson gson=new Gson();
	    list=gson.fromJson(jsonString, new TypeToken<CreatTalkGroup>(){}.getType());
		
		return new CreatTalkGroupResponse(list);
	}

	public void sendRequest(int taskId,String id,String userid) {
		RegisterRequest request = new RegisterRequest();
		JSONObject jsonObject = new JSONObject();
		try {
		jsonObject.put("Machine", request.machine);
		jsonObject.put("MType", request.type);
		jsonObject.put("ScreenSize", request.screen);
		jsonObject.put("IMEI", request.imei);
		jsonObject.put("GPS", "gps");
		jsonObject.put("SessionId",id);
		jsonObject.put("UserId", userid);
			new RemoteHandle(this, request, jsonObject.toString(), taskId).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
