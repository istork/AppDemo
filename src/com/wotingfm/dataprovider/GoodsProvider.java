package com.wotingfm.dataprovider;


import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wotingfm.common.TaskConstant;
import com.wotingfm.model.GoodsAll;
import com.wotingfm.remotehandle.RemoteHandle;
import com.wotingfm.request.RegisterRequest;
import com.wotingfm.response.GoodsResponse;
import com.wotingfm.service.ILogicService;

import android.content.Context;
import android.util.Log;

public class GoodsProvider extends DefaultDataProvider {

	private JSONObject jsonObject;
     
	public GoodsProvider(Context context, ILogicService logicService) {
		super(context, logicService);
		// TODO Auto-generated constructor stub
	}
    
	@Override
	public void process(Object object) {
		if(object!=null&&object instanceof GoodsResponse){
			GoodsResponse response = (GoodsResponse) object;
			if(logicService.getTaskId()==TaskConstant.TASK_GOODS){
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
		///////////////////////////////////////////////////////////////////
		if (jsonString!=null&&jsonString.startsWith("\ufeff")) {
			jsonString=jsonString.substring(1);
		}
		GoodsAll list = new GoodsAll();
			Gson gson=new Gson();
			list=gson.fromJson(jsonString, new TypeToken<GoodsAll>(){}.getType());
		return new GoodsResponse(list);
	
	}

	public void GoodsRequest(int taskId,String seller_nick,String timestamp) {
		RegisterRequest request = new RegisterRequest();
		JSONObject jsonObject = new JSONObject();
		try {
			
			jsonObject.put("sign", "414C9D1FCE0AFF989A8AF77CCD248AC7");
			jsonObject.put("timestamp", timestamp);
			jsonObject.put("app_key", "23224815");
			jsonObject.put("method", "taobao.tae.items.select");
			jsonObject.put("partner_id", "top-apitools");
			jsonObject.put("session", "6101c01431be59368d261d7bb367429fdbda925598d103c837275216");
			jsonObject.put("seller_nick", seller_nick);
			Log.i("request", jsonObject+"");
			new RemoteHandle(this, request, jsonObject.toString(), taskId).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

