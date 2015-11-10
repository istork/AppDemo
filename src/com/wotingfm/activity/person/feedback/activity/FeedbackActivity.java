package com.wotingfm.activity.person.feedback.activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.shenstec.activity.BaseActivity;
import com.wotingfm.R;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.PhoneMessage;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;

public class FeedbackActivity extends BaseActivity implements OnClickListener {
	private EditText mEditContent;
	private EditText mEditContact;
	private TextView mBtnFeedback;
	private LinearLayout mHeadLeftln;
	private LinearLayout mHeadRightln;
	private Dialog dialog;
	private String sEditContent;
	private String sEditContact;
	private boolean flag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		setView();
		setListener();
	}

	private void setView() {
		mEditContent = (EditText) findViewById(R.id.edit_feedback_content);
		mEditContact = (EditText) findViewById(R.id.edit_feedback_contact);
		mBtnFeedback = (TextView) findViewById(R.id.submit_button);
		mHeadLeftln = (LinearLayout) findViewById(R.id.head_left_btn);
		mHeadRightln = (LinearLayout) findViewById(R.id.lin_right_btn);
	}

	private void setListener() {
		mBtnFeedback.setOnClickListener(this);
		mHeadLeftln.setOnClickListener(this);
		mHeadRightln.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		super.onClick(v);
		switch (v.getId()) {
		case R.id.submit_button:
			Boolean result = checkdata();
			if (result == true) {
				send();
			}
			break;
		case R.id.head_left_btn:
			finish();
			break;
		case R.id.lin_right_btn:
			break;
		default:
			break;
		}
	}
	private Boolean checkdata() {
		sEditContent=mEditContent.getText().toString().trim();      
		sEditContact=mEditContact.getText().toString().trim();
		flag=true;             
		if ("".equalsIgnoreCase(sEditContent)) {
			Toast.makeText(this, "请您输入您的意见", 1).show();
			flag = false;
			return flag;
		}
		if ("".equalsIgnoreCase(sEditContact)) {
			Toast.makeText(this, "请您输入您的联系方式", 1).show();
			flag = false;
			return flag;
		}
		
		return flag;
	}

	private void send() {
		RequestQueue requestQueue = Volley.newRequestQueue(this);
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
					ToastUtil.show_short(getApplicationContext(),
							"您的反馈已经提交成功，查看问题解决状态请点击右上角'已提交问题'查看");
				}
				if (ReturnType != null && ReturnType.equals("1002")) {
					ToastUtil.show_short(getApplicationContext(), "提交失败，失败原因"
							+ Message);
				} else {
					if (Message != null && !Message.trim().equals("")) {
						ToastUtil.show_short(getApplicationContext(), Message
								+ "提交意见反馈失败，请稍后重试");
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

			jsonObject.put("Machine", PhoneMessage.productor);
			jsonObject.put("MobileType", PhoneMessage.model);
			jsonObject.put("ScreenSize", PhoneMessage.ScreenWidth + "x"
					+ PhoneMessage.ScreenHeight);
			jsonObject.put("IMEI", PhoneMessage.imei);
			jsonObject.put("GPS", PhoneMessage.getGps(this));
			JsonObjectRequest request = new JsonObjectRequest(
					Request.Method.POST, GlobalConfig.modifyPasswordUrl,
					jsonObject, listener, errorListener);
			requestQueue.add(request);
			requestQueue.start();
			dialog = Utils.Dialogph(this, "正在获取数据", dialog);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
