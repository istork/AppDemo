package com.wotingfm.activity.person.modifypassword.activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenstec.activity.BaseActivity;
import com.wotingfm.R;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.PhoneMessage;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;

public class ModifyPasswordActivity extends BaseActivity {
	private EditText et_oldpassword;
	private EditText et_newpassword;
	private EditText et_newpassword_confirm;
	private Button btn_password_modify;
	private boolean flag;
	private String oldpassword;
	private String newpassword;
	private String passwordconfirm;
	private Dialog dialog;
	private LinearLayout lin_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_password);
		setView();
		setListener();
	}

	private void setView() {
		et_oldpassword = (EditText) findViewById(R.id.edit_oldpassword);
		et_newpassword = (EditText) findViewById(R.id.edit_newpassword);
		et_newpassword_confirm = (EditText) findViewById(R.id.edit_confirmpassword);
		btn_password_modify = (Button) findViewById(R.id.btn_modifypassword);
		lin_back = (LinearLayout) findViewById(R.id.head_left_btn);
	}

	private void setListener() {
		btn_password_modify.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Boolean result = checkData();
				if (result == true) {
					send();
				}

			}
		});
		lin_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	protected Boolean checkData() {
		oldpassword = et_oldpassword.getText().toString().trim();

		newpassword = et_newpassword.getText().toString().trim();

		passwordconfirm = et_newpassword_confirm.getText().toString().trim();

		flag = true;
		if ("".equalsIgnoreCase(oldpassword)) {
			Toast.makeText(this, "请输入您的旧密码", 1).show();
			flag = false;
			return flag;
		}
		if ("".equalsIgnoreCase(newpassword)) {
			Toast.makeText(this, "请输入您的新密码", 1).show();
			flag = false;
			return flag;
		}
		if (newpassword.length() < 6) {
			Toast.makeText(this, "密码请输入六位以上", 1).show();
			flag = false;
			return flag;
		}
		if ("".equalsIgnoreCase(newpassword)) {
			Toast.makeText(this, "请再次输入密码", 1).show();
			flag = false;
			return flag;
		}
		if (!newpassword.equals(passwordconfirm)) {
			new AlertDialog.Builder(this).setMessage("两次输入的密码不一致")
					.setPositiveButton("确定", null).show();
			flag = false;
			return flag;
		}
	
		if (passwordconfirm.length() < 6) {
			Toast.makeText(this, "密码请输入六位以上", 1).show();
			flag = false;
			return flag;
		}

		return flag;
	}

	protected void send() { 
		RequestQueue requestQueue = Volley
				.newRequestQueue(ModifyPasswordActivity.this);
		Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
			private String SessionId;
			private String ReturnType;
			private String GroupName;
			private String Message;
			private String PlayHistory;

			@Override
			// 此处肯定需要返回一个节目信息的list
			public void onResponse(JSONObject arg0) {
				if (dialog != null) {
					dialog.dismiss();
				}
				try {
					// 获取列表
					PlayHistory = arg0.getString("PlayHistory");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Gson gson = new Gson();

				try {
					SessionId = arg0.getString("SessionId");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ReturnType = arg0.getString("ReturnType");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Message = arg0.getString("Message");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (ReturnType != null && ReturnType.equals("1001")) {
					ToastUtil.show_short(ModifyPasswordActivity.this, "密码修改成功");
				}
				if (ReturnType != null && ReturnType.equals("1002")) {
					ToastUtil.show_short(ModifyPasswordActivity.this,
							"创建失败，失败原因" + Message);
				} else {
					if (Message != null && !Message.trim().equals("")) {
						ToastUtil.show_short(ModifyPasswordActivity.this,
								Message + "获取历史消息，请稍后重试");
					}
				}

			}
		};
		ErrorListener errorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
			}
		};
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("SessionId", Utils.getSessionId(this));
			jsonObject.put("UserId", Utils.getUserId(this));
			jsonObject.put("Password", newpassword);
			jsonObject.put("Machine", PhoneMessage.productor);
			jsonObject.put("MobileType", PhoneMessage.model);
			jsonObject.put("ScreenSize", PhoneMessage.ScreenWidth + "x"
					+ PhoneMessage.ScreenHeight);
			jsonObject.put("IMEI", PhoneMessage.imei);
			jsonObject.put("GPS", PhoneMessage.getGps(this));
			JsonObjectRequest request = new JsonObjectRequest(
					Request.Method.POST, GlobalConfig.FeedBackUrl,
					jsonObject, listener, errorListener);
			requestQueue.add(request);
			requestQueue.start();
			dialog = Utils.Dialogph(this, "正在获取数据", dialog);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
