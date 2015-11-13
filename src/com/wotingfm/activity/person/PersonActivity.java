 package com.wotingfm.activity.person;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenstec.activity.annotation.ViewInject;
import com.wotingfm.R;
import com.wotingfm.activity.login.forgetpassword.activity.ForgetPasswordActivity;
import com.wotingfm.activity.login.login.activity.LoginActivity;
import com.wotingfm.activity.login.register.activity.RegisterActivity;
import com.wotingfm.activity.person.binding.AccountBinDingActivity;
import com.wotingfm.activity.person.feedback.activity.FeedbackActivity;
import com.wotingfm.activity.person.modifypassword.activity.ModifyPasswordActivity;
import com.wotingfm.activity.person.playhistory.activity.PlayHistoryActivity;
import com.wotingfm.activity.person.uploadavatar.activity.TxActivity;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.utils.ImageLoader;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;
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
	private SharedPreferences sp;
	private String username;
	private LinearLayout lin_uploadtx;

	private SharedPreferences sharedPreferences;
	private String islogin;
	private ImageLoader imgloader;
	private ImageView touxiang;
	private LinearLayout lin_playhistory;
	private LinearLayout logout_forget;
	private LinearLayout lin_modify;
	private LinearLayout lin_bingding;
	private LinearLayout lin_feedback;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person);
		setview();//设置界面
		setLisener();//设置监听
		imgloader=new ImageLoader(PersonActivity.this);
	}
	
	private void setview() {
		// TODO Auto-generated method stub
		logout_forget=(LinearLayout)findViewById(R.id.logout_forget);//找回密码
		lin_uploadtx=(LinearLayout)findViewById(R.id.uploadtxlayout);//上传头像
		lin_bingding=(LinearLayout)findViewById(R.id.lin_bingding);//账户绑定
		lin_playhistory=(LinearLayout)findViewById(R.id.lin_PlayHistory);//播放历史
		lin_modify=(LinearLayout)findViewById(R.id.lin_modify_pwd);//修改密码
		lin_feedback=(LinearLayout)findViewById(R.id.lin_feedback);//意见反馈
		touxiang=(ImageView)findViewById(R.id.img_hardimagdenglu);//
	}
	
	private void setLisener() {
		lin_playhistory.setOnClickListener(this);
		lin_uploadtx.setOnClickListener(this);
		logout_forget.setOnClickListener(this);
		lin_modify.setOnClickListener(this);
		lin_feedback.setOnClickListener(this);
		lin_bingding.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		//设置监听
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_login:
			startActivity(new Intent(this, LoginActivity.class));
			break;
		case R.id.tv_register:
			Intent intent = new Intent(this,RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.logout_forget:
			startActivity(new Intent(PersonActivity.this,ForgetPasswordActivity.class));
			break;
		case R.id.uploadtxlayout:
			//长传头像
			startActivity(new Intent(this,TxActivity.class));
			break;
		case R.id.lin_PlayHistory:
			//播放历史
			startActivity(new Intent(this,PlayHistoryActivity.class));	
			break;
		case R.id.lin_modify_pwd:
			startActivity(new Intent(this,ModifyPasswordActivity.class));	
			break;
		case R.id.lin_bingding:
			//账户绑定
			startActivity(new Intent(this,AccountBinDingActivity.class));
			break;
		case R.id.lin_feedback:
			//意见反馈
			startActivity(new Intent(this,FeedbackActivity.class));
			break;
		default:
			break;
		}
	}
	@Override
	protected void onResume() {
		// 再次进入该界面加载的数据
		sharedPreferences = this.getSharedPreferences("wotingfm",Context.MODE_PRIVATE);
		islogin = sharedPreferences.getString(StringConstant.ISLOGIN, "false");//
		String imagurl = sharedPreferences.getString(StringConstant.IMAGEURL, "imageurl");//
		if(islogin.equals("true")){
			lin_show.setVisibility(View.VISIBLE);
			lin_login.setVisibility(View.GONE);
			username = sharedPreferences.getString(StringConstant.USERNAME, "");//用户名，昵称
			tv_name.setText(username);
			if(!imagurl.trim().equals("imageurl")){
				imgloader.DisplayImage(imagurl.replace("\\/", "/"), touxiang, false,true,null,null);
				touxiang.setImageURI(Uri.parse(imagurl));
			}		
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
