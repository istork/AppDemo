package com.xl.demo.activity.login;


import java.util.Map;
import java.util.Map.Entry;

import com.shenstec.activity.annotation.ViewInject;
import com.xl.demo.GlobalConfig;
import com.xl.demo.R;
import com.xl.demo.activity.main.MainActivity;
import com.xl.demo.commactivity.BaseActivity;
import com.xl.demo.common.StringConstant;
import com.xl.demo.common.TaskConstant;
import com.xl.demo.service.LoginService;
import com.xl.demo.utils.ToastUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Administrator
 * @denglu
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
			username=edittext_username.getText().toString().trim();
			password=edittext_password.getText().toString().trim();
			ToastUtil.show_short(this, "提交数据中");
			send();
			break;
		case R.id.btn_register:
			Intent intent = new Intent(this,RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.tv_wjmm:
			break;

		default:
			break;
		}
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
