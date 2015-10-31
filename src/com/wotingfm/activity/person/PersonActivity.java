package com.wotingfm.activity.person;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shenstec.activity.annotation.ViewInject;
import com.wotingfm.R;
import com.wotingfm.activity.login.forgetpassword.activity.ForgetPasswordActivity;
import com.wotingfm.activity.login.login.activity.LoginActivity;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.activity.main.MainActivity;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;

public class PersonActivity extends BaseActivity implements OnClickListener {

	@ViewInject(id = R.id.tv_login, click = true)
	private TextView tv_login;
	private LinearLayout layout;
	private SharedPreferences sp;
	private String username;
	private RegisterRequest request;
	private boolean flag=true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person);
		CheckData();
		
	}
	private void CheckData() {
	/*	// TODO Auto-generated method stub
	    sp=getSharedPreferences("wotingfm", Context.MODE_PRIVATE);
	    username=sp.getString(StringConstant.USERNAME, null);
		String islogin=sp.getString(StringConstant.ISLOGIN, null);
		if(islogin.equals("islogin")){
		layout.setEnabled(true);	
		}else{
			layout.setEnabled(false);
		}		*/
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_login:
			startActivity(new Intent(this, LoginActivity.class));
			break;
		case R.id.logout_forget_:
			startActivity(new Intent(PersonActivity.this,ForgetPasswordActivity.class));
		default:
			break;
		}
	}
	private void send() {
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject arg0) {
				Log.e("arg0", arg0 + "");
			}
		};
		ErrorListener errorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.e("arg0", arg0 + "");
			}
		};
		JSONObject jsonObject = new JSONObject();
		try {/////////////////////此处需更改
			jsonObject.put("Machine", request.machine);
		    jsonObject.put("MobileType", request.type);
		    jsonObject.put("ScreenSize", request.screen);
		    jsonObject.put("IMEI", request.imei);
			jsonObject.put("UserName",username);
			JsonObjectRequest request = new JsonObjectRequest(
					Request.Method.POST, GlobalConfig.logoutUrl,jsonObject,
					listener, errorListener);
			requestQueue.add(request);
			requestQueue.start();
			Log.i("获取绑定路径及信息", GlobalConfig.logoutUrl + jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(this,"正在注销当中,请稍等", 1).show();

	}

	public void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	long waitTime = 2000;
	long touchTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - touchTime) >= waitTime) {
				ToastUtil.show_short(PersonActivity.this, "再按一次退出");
				touchTime = currentTime;
			} else {
				android.os.Process.killProcess(android.os.Process.myPid());
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
