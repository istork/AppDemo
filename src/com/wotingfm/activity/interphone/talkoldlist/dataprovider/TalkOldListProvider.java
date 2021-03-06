package com.wotingfm.activity.interphone.talkoldlist.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wotingfm.activity.interphone.talkoldlist.model.TalkOldList;
import com.wotingfm.activity.interphone.talkoldlist.response.TalkOldListResponse;
import com.wotingfm.activity.interphone.talkperson.model.TalkPerson;
import com.wotingfm.activity.interphone.talkperson.response.TalkPersonResponse;
import com.wotingfm.activity.login.login.model.loginmessage;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.dataprovider.DefaultDataProvider;
import com.wotingfm.main.remotehandle.RemoteHandle;
import com.wotingfm.main.service.ILogicService;
import com.wotingfm.utils.Utils;

public class TalkOldListProvider extends DefaultDataProvider {

	public TalkOldListProvider(Context context, ILogicService logicService) {
		super(context, logicService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(Object object) {
		if(object!=null&&object instanceof TalkOldListResponse){
			TalkOldListResponse response = (TalkOldListResponse) object;
			if(logicService.getTaskId()==TaskConstant.Task_TalkOldList){
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
		
//		List<TalkOldList> list = new ArrayList<TalkOldList>();
//		Gson gson=new Gson();
//		list=gson.fromJson(jsonString, new TypeToken<List<TalkOldList>>(){}.getType());
		
		
		TalkOldList list = new TalkOldList();
	    Gson gson=new Gson();
	    list=gson.fromJson(jsonString, new TypeToken<TalkOldList>(){}.getType());
		
		return new TalkOldListResponse(list);
	}

	public void sendRequest(int taskId) {
		RegisterRequest request = new RegisterRequest();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("Machine", request.machine);
			jsonObject.put("MType", request.type);
			jsonObject.put("ScreenSize", request.screen);
			jsonObject.put("IMEI", request.imei);
			jsonObject.put("GPS", "gps");
			jsonObject.put("SessionId",Utils.getSessionId(context));
			jsonObject.put("UserId", Utils.getUserId(context));
			new RemoteHandle(this, request, jsonObject.toString(), taskId).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
