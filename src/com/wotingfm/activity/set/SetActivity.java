package com.wotingfm.activity.set;


import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wotingfm.R;
import com.wotingfm.activity.interphone.grouplist.service.TalkGroupListService;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.BSApplication;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SetActivity extends BaseActivity implements OnClickListener {
	private LinearLayout zhuxiao;
	private SetActivity context;
	private Dialog dialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		context=this;
		zhuxiao=(LinearLayout)findViewById(R.id.lin_zhuxiao);
		zhuxiao.setOnClickListener(context);
		
	}


	private void send() {
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
			
			private String ReturnType;
			private String SessionId;
			private String Message;

			@Override
			public void onResponse(JSONObject arg0) {
				if(dialog!=null){
					dialog.dismiss();
				}
				Log.e("注销返回数据", arg0 + "");
				try {
					ReturnType=	arg0.getString("ReturnType");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					SessionId=	arg0.getString("SessionId");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Message=	arg0.getString("Message");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(ReturnType.equals("1001")){
					
					SharedPreferences sp=getSharedPreferences("wotingfm", Context.MODE_PRIVATE);
					Editor et=sp.edit();
					et.putString(StringConstant.ISLOGIN, "false");
					et.commit();
					Toast.makeText(context,"注销成功,请稍等", 1).show();
				}else{
					Toast.makeText(context,"注销失败,请稍等", 1).show();
				}
			}
		};
		ErrorListener errorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				if(dialog!=null){
					dialog.dismiss();
				}
				// TODO Auto-generated method stub
				Log.e("注销返回异常", arg0 + "");
			}
		};
		JSONObject jsonObject = new JSONObject();
		RegisterRequest requests = new RegisterRequest();
		try {
			jsonObject.put("Machine", requests.machine);
		    jsonObject.put("MobileType", requests.type);
		    jsonObject.put("ScreenSize", requests.screen);
		    jsonObject.put("IMEI", requests.imei);
			jsonObject.put("SessionId",Utils.getSessionId(this));
			JsonObjectRequest request = new JsonObjectRequest(
					Request.Method.POST, GlobalConfig.logoutUrl,jsonObject,
					listener, errorListener);
			requestQueue.add(request);
			requestQueue.start();
			Log.i("获取注销路径及信息", GlobalConfig.logoutUrl + jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.lin_zhuxiao:
			
			
				if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
					dialog = Utils.Dialogph(context, "正在获取数据", dialog);
					send();
				}else{
					ToastUtil.show_short(context, "网络失败，请检查网络");
				}

			break;

		default:
			break;
		}
	}
	
}
