package com.wotingfm.activity.person.binding;


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
import com.shenstec.activity.BaseActivity;
import com.wotingfm.R;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
/*
 * 账户绑定 
 * 辛龙
 */
public class AccountBinDingActivity extends BaseActivity implements OnClickListener {

	private LinearLayout head_left_btn;
	private AccountBinDingActivity context;
	private LinearLayout left;
	private LinearLayout right;
	private LinearLayout bingding;
	private int bingding_type;
	private ImageView image_left;
	private ImageView image_right;
	private EditText et_name;
	private Dialog dialog;
	private LinearLayout head_right_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_binding);
		context=this;
		//默认绑定手机号.1,2为邮箱
		bingding_type=1;
		setview();//设置界面
		setlistener();//对界面设置监听
	}

	private void setview() {
		head_right_btn=(LinearLayout)findViewById(R.id.head_right_btn);//返回
		head_left_btn=(LinearLayout)findViewById(R.id.head_left_btn);//返回
		left=(LinearLayout)findViewById(R.id.lin_left);//手机按钮
		right=(LinearLayout)findViewById(R.id.lin_right);//邮箱按钮
		bingding=(LinearLayout)findViewById(R.id.lin_bingding);//

		et_name=(EditText)findViewById(R.id.et_name);//账户输入编辑框

		image_left=(ImageView)findViewById(R.id.image_left);//手机图标
		image_right=(ImageView)findViewById(R.id.image_right);//邮箱图标
		//设置初始化界面
		image_left.setImageResource(R.drawable.ic_detail_item_batch_download_pressed);
		image_right.setImageResource(R.drawable.ic_detail_item_batch_download_normal);
		et_name.setHint("请输入要绑定的手机号");

	}

	private void setlistener() {
		head_right_btn.setOnClickListener(context);//解绑
		head_left_btn.setOnClickListener(context);//返回
		left.setOnClickListener(context);//
		right.setOnClickListener(context);//
		bingding.setOnClickListener(context);//
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.head_left_btn://返回
			finish();
			break;
		case R.id.lin_left://点击绑定手机号
			image_left.setImageResource(R.drawable.ic_detail_item_batch_download_pressed);
			image_right.setImageResource(R.drawable.ic_detail_item_batch_download_normal);
			et_name.setText("");
			et_name.setHint("请输入要绑定的手机号");
			bingding_type=1;
			break;
		case R.id.lin_right://点击绑定邮箱
			image_left.setImageResource(R.drawable.ic_detail_item_batch_download_normal);
			image_right.setImageResource(R.drawable.ic_detail_item_batch_download_pressed);
			et_name.setText("");
			et_name.setHint("请输入要绑定的邮箱");
			bingding_type=2;
			break;
		case R.id.lin_bingding://提交数据
			judge();
			break;
		default:
			break;
		}
	}

	private void judge() {
		// 判断手机号以及邮箱格式
		/*
		 * 需要加正则判断
		 * 正确后再send()
		 * 
		 */
		
		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			dialog = Utils.Dialogph(context, "绑定中，请稍等", dialog);
			send();
		}else{
			ToastUtil.show_short(context, "网络失败，请检查网络");
		}
	}

	private void send() {
		// 发送数据
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
					Toast.makeText(context,"绑定成功", 1).show();
				}else{
					Toast.makeText(context,"绑定失败，请稍后再试", 1).show();
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
				Log.e("绑定返回异常", arg0 + "");
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
			Log.i("获取绑定路径及信息", GlobalConfig.logoutUrl + jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
