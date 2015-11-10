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
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.activity.person.PersonActivity;
import com.wotingfm.activity.set.about.AboutActivity;
import com.wotingfm.activity.set.help.HelpActivity;
import com.wotingfm.activity.set.update.UpdateManager;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.PhoneMessage;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SetActivity extends BaseActivity implements OnClickListener {
	private LinearLayout zhuxiao;
	private SetActivity context;
	private Dialog dialog;
	private LinearLayout updateaddress;
	private LinearLayout clear;
	private LinearLayout update;
	private LinearLayout help;
	private LinearLayout about;
	private Dialog updatedialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		context=this;
		setview();//设置界面
		setlistener();//对界面设置监听
		UpdateDialog();//更新的弹出框
	}

	private void setview() {
		zhuxiao=(LinearLayout)findViewById(R.id.lin_zhuxiao);//注销
		updateaddress=(LinearLayout)findViewById(R.id.lin_updateaddress);//下载位置
		clear=(LinearLayout)findViewById(R.id.lin_clear);//清除缓存
		update=(LinearLayout)findViewById(R.id.lin_update);//检查更新
		help=(LinearLayout)findViewById(R.id.lin_help);//我听帮助
		about=(LinearLayout)findViewById(R.id.lin_about);//关于
	}

	private void setlistener() {
		zhuxiao.setOnClickListener(context);
		updateaddress.setOnClickListener(context);//下载位置
		clear.setOnClickListener(context);//清除缓存
		update.setOnClickListener(context);//检查更新
		help.setOnClickListener(context);//我听帮助
		about.setOnClickListener(context);//关于
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.lin_zhuxiao://注销登录
			if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
				dialog = Utils.Dialogph(context, "正在获取数据", dialog);
				zhuxiao();
			}else{
				ToastUtil.show_short(context, "网络失败，请检查网络");
			}
			break;
		case R.id.lin_updateaddress://下载位置
			ToastUtil.show_short(context, "稍后做");
			break;
		case R.id.lin_clear://清除缓存
			ToastUtil.show_short(context, "稍后做");
			break;
		case R.id.lin_update://检查更新
			updatedialog.show();
			//			if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			//				dialog = Utils.Dialogph(context, "通讯中", dialog);
			//				update();
			//			}else{
			//				ToastUtil.show_short(context, "网络失败，请检查网络");
			//			}
			break;
		case R.id.lin_help://我听帮助
			Intent helpintent=new Intent(context,HelpActivity.class);
			startActivity(helpintent);
			break;
		case R.id.lin_about://关于
			Intent aboutintent=new Intent(context,AboutActivity.class);
			startActivity(aboutintent);
			break;
		default:
			break;
		}
	}
	private void update() {
		// 更新数据交互
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
				Log.e("检查更新返回数据", arg0 + "");
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
					updatedialog.show();
				}else{
					Toast.makeText(context,"暂无新版本", 1).show();
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
				Log.e("检查更新返回异常", arg0 + "");
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
			jsonObject.put("Version",PhoneMessage.versionCode);
			JsonObjectRequest request = new JsonObjectRequest(
					Request.Method.POST, GlobalConfig.logoutUrl,jsonObject,
					listener, errorListener);
			requestQueue.add(request);
			requestQueue.start();
			Log.i("检查更新路径及信息", GlobalConfig.logoutUrl + jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void zhuxiao() {
		// 注销数据交互
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
					zhuxiao.setVisibility(View.INVISIBLE);
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


	private void UpdateDialog() {
		// 更新弹出框
		View dialog=LayoutInflater.from(this).inflate(R.layout.dialog_update, null);
		TextView tv_update = (TextView) dialog.findViewById(R.id.tv_update);
		TextView tv_qx = (TextView) dialog.findViewById(R.id.tv_qx);
		tv_update.setOnClickListener(this);
		tv_qx.setOnClickListener(this);
		updatedialog = new Dialog(this, R.style.MyDialog);
		updatedialog.setContentView(dialog);
		updatedialog.setCanceledOnTouchOutside(false);
		updatedialog.getWindow().setBackgroundDrawableResource(R.color.dialog);
		tv_update.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				okupdate();//开始更新
				updatedialog.dismiss();

			}
		});
		tv_qx.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updatedialog.dismiss();

			}
		});
	}

	protected void okupdate() {
		// 调用更新功能
		UpdateManager updateManager = new UpdateManager(this);
		updateManager.checkUpdateInfo1();
	}
	
	@Override
	protected void onResume() {
		// 重新适配
		super.onResume();
		SharedPreferences sharedPreferences = this.getSharedPreferences("wotingfm",Context.MODE_PRIVATE);
		String islogin = sharedPreferences.getString(StringConstant.ISLOGIN, "false");//
		if(islogin.equals("true")){
			zhuxiao.setVisibility(View.VISIBLE);
		}else{
			zhuxiao.setVisibility(View.INVISIBLE);
		}
	}
	
	long waitTime = 2000;
	long touchTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - touchTime) >= waitTime) {
				ToastUtil.show_short(SetActivity.this, "再按一次退出");
				touchTime = currentTime;
			} else {
//				android.os.Process.killProcess(android.os.Process.myPid());
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
