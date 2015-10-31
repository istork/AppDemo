package com.wotingfm.activity.login.splash.activity;


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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wotingfm.activity.interphone.grouplist.model.TalkGroupList;
import com.wotingfm.activity.login.WelcomeActivity;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.activity.login.splash.model.UserInfo;
import com.wotingfm.activity.main.MainActivity;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.main.request.DefaultRequest;
import com.wotingfm.utils.Utils;
import com.wotingfm.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class SplashActivity extends Activity {
	//	private Context context;
	public static final int LOADFINISH = 0000;
	private SharedPreferences sharedPreferences;
	private String first;
	private View view;
	private Dialog dialog;
	private SplashActivity context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = View.inflate(this, R.layout.activity_splash, null);
		setContentView(view);
		context = this;
		sharedPreferences = this.getSharedPreferences("wotingfm",Context.MODE_PRIVATE);
		first = sharedPreferences.getString(StringConstant.FIRST, "0");//用户名，昵称
//		Intent show = new Intent(this, FloatingWindowService.class);
//		show.putExtra(FloatingWindowService.OPERATION, FloatingWindowService.OPERATION_SHOW);
//		startService(show);
		setview();
		//		new Handler().postDelayed(new MyRunnable(), LOADFINISH);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				send();
		
			}
		}, LOADFINISH);

	}



	protected void send() {
		// TODO Auto-generated method stub
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
			
			private String ReturnType;
			private String SessionId;
			private String UserInfos;

			@Override
			public void onResponse(JSONObject arg0) {
				if(dialog!=null){
					dialog.dismiss();
				}
				Log.e("启动返回数据", arg0 + "");
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
					UserInfos=	arg0.getString("UserInfo");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//				 UserInfo list = new UserInfo();
//			    Gson gson=new Gson();
//			    list=gson.fromJson(UserInfos, new TypeToken<UserInfo>(){}.getType());
//			    String userid = list.getUserId();
//			    String username = list.getUserName();
			    SharedPreferences sp=getSharedPreferences("wotingfm", Context.MODE_PRIVATE);
				//测试信息
				if(ReturnType.equals("1001")){
					Toast.makeText(context,"登录成功", 1).show();
				}else if(ReturnType.equals("1002")){
					Toast.makeText(context,"登录失败,请稍后再试", 1).show();
				}else{
					Toast.makeText(context,"登录异常,请稍后再试", 1).show();
				}
				
				Editor et=sp.edit();
				et.putString(StringConstant.SESSIONID, SessionId); 
				et.commit();
				if(first!=null&&first.equals("1")){
					startActivity(new Intent(SplashActivity.this, MainActivity.class));
				}else{
					startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
				}

				finish();
				
				
			}
		};
		ErrorListener errorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				if(dialog!=null){
					dialog.dismiss();
				}
				// TODO Auto-generated method stub
				Log.e("启动异常", arg0 + "");
			}
		};
		JSONObject jsonObject = new JSONObject();
		RegisterRequest requests = new RegisterRequest();
		try {
			jsonObject.put("Machine", requests.machine);
		    jsonObject.put("MobileType", requests.type);
		    jsonObject.put("ScreenSize", requests.screen);
		    jsonObject.put("IMEI", requests.imei);
//			jsonObject.put("SessionId",Utils.getSessionId(this));
			JsonObjectRequest request = new JsonObjectRequest(
					Request.Method.POST, GlobalConfig.splashUrl,jsonObject,
					listener, errorListener);
			requestQueue.add(request);
			requestQueue.start();
			Log.i("启动路径及信息", GlobalConfig.splashUrl + jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private void setview() {
		// TODO Auto-generated method stub
		// 设置动画效果是alpha，在anim目录下的alpha.xml文件中定义动画效果
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
		// 给view设置动画效果
		view.startAnimation(animation);
//		animation.setAnimationListener(new AnimationListener() {
//			@Override
//			public void onAnimationStart(Animation arg0) {
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation arg0) {
//			}
//
//			// 这里监听动画结束的动作，在动画结束的时候开启一个线程，这个线程中绑定一个Handler,并
//			// 在这个Handler中调用goHome方法，而通过postDelayed方法使这个方法延迟500毫秒执行，达到
//			// 达到持续显示第一屏500毫秒的效果
//			@Override
//			public void onAnimationEnd(Animation arg0) {
//			}
//		});
	}
}



//	class MyRunnable implements Runnable {
//
//		@Override
//		public void run() {
//			SplashActivity.this.finish();
//			startActivity(new Intent(SplashActivity.this, MainActivity.class));
//		}
//
//	}


