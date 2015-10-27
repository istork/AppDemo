package com.wotingfm.activity.login.forgetpassword.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wotingfm.R;
import com.wotingfm.activity.login.forgetpassword.service.ForgetPasswordService;
import com.wotingfm.activity.login.register.service.RegisterService;
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
	}
	private void send() {
		// TODO Auto-generated method stub
		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			int randomX = (int) System.currentTimeMillis();
			new ForgetPasswordService(this, this, TaskConstant.Task_Forget).sendRegisterRequest(randomX,username,userphone,useremail );
		}else{
			ToastUtil.show_short(this, "网络失败，请检查网络");
		}
	}

}
