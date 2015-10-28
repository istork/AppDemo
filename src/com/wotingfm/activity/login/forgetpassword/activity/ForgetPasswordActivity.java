package com.wotingfm.activity.login.forgetpassword.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wotingfm.R;
import com.wotingfm.activity.login.forgetpassword.model.ForgetMessage;
import com.wotingfm.activity.login.forgetpassword.response.ForgetPasswordResponse;
import com.wotingfm.activity.login.forgetpassword.service.ForgetPasswordService;
import com.wotingfm.activity.login.resetpassword.activity.ResetPassWordActivity;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;

public class ForgetPasswordActivity extends BaseActivity implements OnClickListener{
		
	private EditText mforgetname;
	private EditText mforgetphone;
	private EditText mforgetemail;
	private LinearLayout layout;
	private TextView mforgetBtn;
	private String username;
	private String userphone;
	private String useremail;
	private ForgetMessage list;
	private String rttype;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password);	
		mforgetBtn=(TextView)findViewById(R.id.btn_forget);
		mforgetname=(EditText)findViewById(R.id.edittext_name);
		mforgetphone=(EditText)findViewById(R.id.edittext_phone);
		mforgetemail=(EditText)findViewById(R.id.edittext_email);
		layout=(LinearLayout)findViewById(R.id.head_left_btn);
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		mforgetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			checkdata();							
			}
		});
	}


	private void checkdata() {
		// TODO Auto-generated method stub
		username=mforgetname.getText().toString().trim();
		userphone=mforgetphone.getText().toString().trim();
	    useremail=mforgetemail.getText().toString().trim();
		  if("".equalsIgnoreCase(username)){
			   
			  mforgetname.setError(Html.fromHtml("<font color=#ff0000>用户名不能为空</font>"));
	            return;
			}
			
			if("".equalsIgnoreCase(userphone)){
				mforgetphone.setError(Html.fromHtml("<font color=#ff0000>密码不能为空</font>"));
				return;
			}
			
	        if("".equalsIgnoreCase(useremail)){
	        	mforgetemail.setError(Html.fromHtml("<font color=#ff0000>请再次输入密码</font>"));
				return;

			}
	    	ToastUtil.show_short(this, "提交数据中");
			send();	  
			startActivity(new Intent(ForgetPasswordActivity.this,ResetPassWordActivity.class));	
	}
	private void send() {
		// TODO Auto-generated method stub
		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			int randomX = (int) System.currentTimeMillis();
			new ForgetPasswordService(this, this, TaskConstant.Task_Forget).sendForgetRequest(randomX,username,userphone,useremail );
		}else{
			ToastUtil.show_short(this, "网络失败，请检查网络");
		}
	}
	public void refreshUI(Message msg) {

		switch (msg.what) {
		case TaskConstant.Task_Forget:
			if(msg.obj!=null && msg.obj instanceof ForgetPasswordResponse){
				ForgetPasswordResponse mForgetPasswordResponse=(ForgetPasswordResponse)msg.obj;
				try {
					 list =mForgetPasswordResponse.list;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rttype=list.getReturnType();
				handleReturnType(rttype);
				Log.i(rttype, rttype);
			}
			break;
		}
	}
	private void handleReturnType(String returntype) {
		// TODO Auto-generated method stub
		int ReturnType=Integer.parseInt(returntype);		
		switch(ReturnType){
		case 1001:
			Toast.makeText(this,"允许修改", 1).show();
			startActivity(new Intent(ForgetPasswordActivity.this,ResetPassWordActivity.class));
			break;
		case 1002:
			Toast.makeText(this,"服务器端无此注册用户", 1).show();
			break;
		case 1003:
			Toast.makeText(this,"服务器端用户名和邮箱信息与提交内容不匹配", 1).show();
			break;
		}	
	}

}
