package com.wotingfm.activity.person;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.shenstec.activity.annotation.ViewInject;
import com.wotingfm.R;
import com.wotingfm.activity.login.forgetpassword.activity.ForgetPasswordActivity;
import com.wotingfm.activity.login.login.activity.LoginActivity;
import com.wotingfm.activity.login.register.activity.RegisterActivity;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.utils.ToastUtil;
/*
 * 个人信息主页
 * 辛龙
 */
public class PersonActivity extends BaseActivity implements OnClickListener {

	@ViewInject(id = R.id.tv_name)
	private TextView tv_name;
	@ViewInject(id = R.id.tv_isbangding)
	private TextView tv_isbangding;
	@ViewInject(id = R.id.tv_login, click = true)
	private TextView tv_login;
	@ViewInject(id = R.id.tv_register, click = true)
	private TextView tv_register;
	@ViewInject(id=R.id.lin_show)
	private LinearLayout lin_show;
	@ViewInject(id=R.id.lin_login)
	private LinearLayout lin_login;
	private LinearLayout layout;
	private SharedPreferences sp;
	private String username;

	private SharedPreferences sharedPreferences;
	private String islogin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person);

	}
	//	private void CheckData() {
	//	/*	// TODO Auto-generated method stub
	//	    sp=getSharedPreferences("wotingfm", Context.MODE_PRIVATE);
	//	    username=sp.getString(StringConstant.USERNAME, null);
	//		String islogin=sp.getString(StringConstant.ISLOGIN, null);
	//		if(islogin.equals("islogin")){
	//		layout.setEnabled(true);	
	//		}else{
	//			layout.setEnabled(false);
	//		}		*/
	//	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_login:
			startActivity(new Intent(this, LoginActivity.class));
			break;
		case R.id.tv_register:
			Intent intent = new Intent(this,RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.logout_forget_:
			startActivity(new Intent(PersonActivity.this,ForgetPasswordActivity.class));
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		sharedPreferences = this.getSharedPreferences("wotingfm",Context.MODE_PRIVATE);
		islogin = sharedPreferences.getString(StringConstant.ISLOGIN, "false");//
		if(islogin.equals("true")){
			lin_show.setVisibility(View.VISIBLE);
			lin_login.setVisibility(View.GONE);
			username = sharedPreferences.getString(StringConstant.USERNAME, "");//用户名，昵称
			tv_name.setText(username);
		}else{
			lin_show.setVisibility(View.GONE);
			lin_login.setVisibility(View.VISIBLE);
		}
		super.onResume();
	}

	
	long waitTime = 2000;
	long touchTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - touchTime) >= waitTime) {
				ToastUtil.show_short(PersonActivity.this, "再按一次退出");
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
