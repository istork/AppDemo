package com.wotingfm.activity.login.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenstec.activity.annotation.ViewInject;
import com.wotingfm.R;
import com.wotingfm.activity.login.forgetpassword.activity.ForgetPasswordActivity;
import com.wotingfm.activity.login.login.service.LoginService;
import com.wotingfm.activity.login.register.activity.RegisterActivity;
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



	

	
}
