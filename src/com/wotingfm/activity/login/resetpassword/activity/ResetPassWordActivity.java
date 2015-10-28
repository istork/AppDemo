package com.wotingfm.activity.login.resetpassword.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wotingfm.R;
import com.wotingfm.activity.login.resetpassword.model.UserInfo;
import com.wotingfm.activity.login.resetpassword.response.ResetPassWordResponse;
import com.wotingfm.activity.login.resetpassword.service.ResetPasswordService;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;

public class ResetPassWordActivity extends BaseActivity {

	private Button Resetbtn;
	private EditText Etpassword;
	private EditText Etpasswordcf;
	private String password;
	private String passwordcf;
	private LinearLayout layout;
	private String rttype;
	private UserInfo list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_password);
		Resetbtn = (Button) findViewById(R.id.btn_reset);
		Etpassword = (EditText) findViewById(R.id.edit_newpassword);
		Etpasswordcf = (EditText) findViewById(R.id.edittext_newpassword_confirm);
		layout = (LinearLayout) findViewById(R.id.head_left_btn);
		setlistener();
	}

	private void setlistener() {
		// TODO Auto-generated method stub
		layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Resetbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkdata();
				Toast.makeText(ResetPassWordActivity.this, "数据提交中", 1).show();
			}
		});

	}

	protected void checkdata() {
		// TODO Auto-generated method stub
		password = Etpassword.getText().toString().trim();
		passwordcf = Etpasswordcf.getText().toString().trim();
		if ("".equalsIgnoreCase(password)) {
			Etpassword.setError(Html
					.fromHtml("<font color=#ff0000>密码不能为空</font>"));
			return;
		}
		if ("".equalsIgnoreCase(passwordcf)) {
			Etpasswordcf.setError(Html
					.fromHtml("<font color=#ff0000>请再次输入密码</font>"));
			return;
		}
		if (!password.equals(passwordcf)) {
			new AlertDialog.Builder(this).setMessage("两次输入的密码不一致")
					.setPositiveButton("确定", null).show();
			return;
		}
		send();
	}

	private void send() {
		// TODO Auto-generated method stub
		if (GlobalConfig.CURRENT_NETWORK_STATE_TYPE != -1) {
			int randomX = (int) System.currentTimeMillis();
			new ResetPasswordService(this,  this,
					TaskConstant.Task_Reset).sendResetPasswordRequest(randomX,
					password);
		} else {
			ToastUtil.show_short(this, "网络失败，请检查网络");
		}
	}
	public void refreshUI(Message msg) {

		switch (msg.what) {
		case TaskConstant.Task_Reset:
			if(msg.obj!=null && msg.obj instanceof ResetPassWordResponse){
				ResetPassWordResponse mResetPassWordResponse=(ResetPassWordResponse)msg.obj;
				try {
					 list =mResetPassWordResponse.list;
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
			Toast.makeText(this,"密码修改成功", 1).show();;
			break;
		case 1002:
			Toast.makeText(this,"密码修改失败", 1).show();;
			break;
		}	
	}

}
