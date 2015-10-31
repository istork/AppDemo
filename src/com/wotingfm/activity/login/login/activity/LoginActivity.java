package com.wotingfm.activity.login.login.activity;


import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shenstec.activity.annotation.ViewInject;
import com.wotingfm.R;
import com.wotingfm.activity.login.SplashActivity;
import com.wotingfm.activity.login.forgetpassword.activity.ForgetPasswordActivity;
import com.wotingfm.activity.login.login.model.UserInfo;
import com.wotingfm.activity.login.login.model.loginmessage;
import com.wotingfm.activity.login.login.response.LoginResponse;
import com.wotingfm.activity.login.login.service.LoginService;
import com.wotingfm.activity.login.register.activity.RegisterActivity;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;
/**
 * 
 * @author Administrator
 * @wangzhi
 */
public class LoginActivity extends BaseActivity implements OnClickListener {
	@ViewInject(id=R.id.head_left_btn,click=true)
	private LinearLayout pubBtn;
	
	@ViewInject(id=R.id.edittext_username)
	private EditText edittext_username;
	
	@ViewInject(id=R.id.edittext_password)
	private EditText edittext_password;
	
	@ViewInject(id=R.id.tv_wjmm,click=true)
	private TextView tv_wjmm;
	
	@ViewInject(id=R.id.btn_login,click=true)
	private Button btn_login;
	
	@ViewInject(id=R.id.btn_register,click=true)
	private Button btn_register;

	private String username;

	private String password;

	private loginmessage list;

	private String rttype;

	private List<UserInfo> ceshilist;

	private LinearLayout layout;

	private String Message;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_login);
    }
    
    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.head_left_btn:
			finish();
			break;
		case R.id.btn_login:
			checkdata();
			break;
		case R.id.btn_register:
			Intent intent = new Intent(this,RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.tv_wjmm:
			Intent PasswordIntent=new Intent(this,ForgetPasswordActivity.class);
			startActivity(PasswordIntent);
			break;

		default:
			break;
		}
	}

	private void checkdata() {
		// TODO Auto-generated method stub
		username=edittext_username.getText().toString().trim();
		password=edittext_password.getText().toString().trim();
		 if("".equalsIgnoreCase(username)){
			    edittext_username.setError(Html.fromHtml("<font color=#ff0000>用户名不能为空</font>"));
	            return;
			}
		 if("".equalsIgnoreCase(password)){
			    edittext_username.setError(Html.fromHtml("<font color=#ff0000>密码不能为空</font>"));
	            return;
			}
		
		ToastUtil.show_short(this, "提交数据中");
		send();
		
	}

	private void send() {
		// TODO Auto-generated method stub
		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			int randomX = (int) System.currentTimeMillis();
			new LoginService(this, this, TaskConstant.TASK_LOGIN).sendRequest(randomX, username, password);
		}else{
			ToastUtil.show_short(this, "网络失败，请检查网络");
		}
	}
	public void refreshUI(Message msg) {

		switch (msg.what) {
		case TaskConstant.TASK_LOGIN:
			if(msg.obj!=null && msg.obj instanceof LoginResponse){
				LoginResponse LoginResponse=(LoginResponse) msg.obj;
			    list = LoginResponse.list;
			    Message=list.getMessage();
			    rttype=list.getReturnType();
				if(rttype!=null&&rttype!=""){
				    handleReturnType(rttype);					
				}else{
					Toast.makeText(LoginActivity.this, "网络状态异常，请稍后重试", 1).show();
				}
				new AlertDialog.Builder(this).setMessage("ReturnType="+rttype+"Message="+Message).
	 			setPositiveButton("确定", null).show();
			}else{
			Toast.makeText(LoginActivity.this, "与服务器通信失败",1).show();
			}
			break;
		}
	}
	private void handleReturnType(String returntype) {
		// TODO Auto-generated method stub
		int ReturnType=Integer.parseInt(returntype);		
		switch(ReturnType){
		case 1001:
			Toast.makeText(this,"登录成功", 1).show();
			//设置全局常量 标记是否是登录状态
			GlobalConfig.islogin=true;
			//通过shareperfrence存储用户的登录信息
			SharedPreferences sp=getSharedPreferences("wotingfm", Context.MODE_PRIVATE);
			Editor et=sp.edit();
			et.putString(StringConstant.ISLOGIN, "islogin");
			et.putString(StringConstant.USERNAME,username);  
			et.commit();
			//延迟跳转页面
			Handler h=new Handler();   
			h.postDelayed(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					startActivity(new Intent(LoginActivity.this,SplashActivity.class));
				}
			}, 1000);	
			break;
		case 1002:
			Toast.makeText(this,"服务器端无此用户", 1).show();
			break;
		case 1003:
			Toast.makeText(this,"密码错误", 1).show();
			break;
		case 0000:
			Toast.makeText(this,"发生未知错误，请稍后重试",1).show();
		}	
	}	
}
