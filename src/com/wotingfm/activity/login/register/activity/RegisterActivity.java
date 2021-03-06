package com.wotingfm.activity.login.register.activity;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shenstec.activity.annotation.ViewInject;
import com.wotingfm.R;
import com.wotingfm.activity.login.register.model.UserInfo;
import com.wotingfm.activity.login.register.response.RegisterResponse;
import com.wotingfm.activity.login.register.service.RegisterService;
import com.wotingfm.activity.main.MainActivity;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;
/**
 * 
 * @author Administrator
 * @wangzhi
 */
public class RegisterActivity extends BaseActivity implements OnClickListener {
	
	@ViewInject(id=R.id.head_left_btn,click=true)
	private LinearLayout pubBtn;	
	@ViewInject(id=R.id.btn_register,click=true)
	private Button btn_register;
	private EditText mEditTextName;
	private EditText mEditTextPassWord;
	private EditText mEditTextPassWordConfirm;
	private String password;
	private String passwordcf;
	private String username;
	private String rttype;
	private UserInfo list;
	private RegisterActivity context;
	private Dialog dialog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_register);
    	context=this;
    	mEditTextName=(EditText)findViewById(R.id.edittext_name);
		mEditTextPassWord=(EditText)findViewById(R.id.edittext_password);
		mEditTextPassWordConfirm=(EditText)findViewById(R.id.edittext_confirm);
    	
    }
    
	private void checkPassWord() {
		// TODO Auto-generated method stub
	    username=mEditTextName.getText().toString().trim();
		password=mEditTextPassWord.getText().toString().trim();
		passwordcf=mEditTextPassWordConfirm.getText().toString().trim();
        if("".equalsIgnoreCase(username)){
        	mEditTextName.setError(Html.fromHtml("<font color=#ff0000>用户名不能为空</font>"));
            return;
		}
//        if(username.length()<6){
//        	mEditTextName.setError(Html.fromHtml("<font color=#ff0000>用户名请输入六位以上</font>"));
//        	return;
//        }
        if(password.length()<6){
        	mEditTextPassWord.setError(Html.fromHtml("<font color=#ff0000>密码请输入六位以上</font>"));
        	return;
        }
        if(passwordcf.length()<6){
        	mEditTextPassWordConfirm.setError(Html.fromHtml("<font color=#ff0000>密码请输入六位以上</font>"));
        	return;
        }
		if("".equalsIgnoreCase(password)){
			mEditTextPassWord.setError(Html.fromHtml("<font color=#ff0000>密码不能为空</font>"));
			return;
		}
        if("".equalsIgnoreCase(passwordcf)){
			mEditTextPassWordConfirm.setError(Html.fromHtml("<font color=#ff0000>请再次输入密码</font>"));
			return;
		}
    	if(!password.equals(passwordcf)){
			new AlertDialog.Builder(this).setMessage("两次输入的密码不一致").
 			setPositiveButton("确定", null).show();   
			return;
		}	
		send();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.head_left_btn:
			finish();
			break;
		case R.id.btn_register:
			checkPassWord();
			break;
		default:
			break;
		}
	}
	
	private void send() {
		// TODO Auto-generated method stub
		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			dialog = Utils.Dialogph(context, "登录中", dialog);
			int randomX = (int) System.currentTimeMillis();
			new RegisterService(this, this, TaskConstant.TASK_REGISTER).sendRegisterRequest(randomX,  username, password);
		}else{
			ToastUtil.show_short(this, "网络连接失败，请检查网络是否正常");
		}
	}
	
	public void refreshUI(Message msg) {
		switch (msg.what) {
		case TaskConstant.TASK_REGISTER:
			if(dialog!=null){
				dialog.dismiss();
			}
			if(msg.obj!=null && msg.obj instanceof RegisterResponse){
				RegisterResponse RegisterResponse=(RegisterResponse) msg.obj;
                list = RegisterResponse.list;			
				String message=list.getMessage();
				rttype=list.getReturnType();
				if(rttype!=null&&rttype!=""){
				    handleReturnType(rttype);					
				}else{
					Toast.makeText(RegisterActivity.this, "网络状态异常，请稍后重试", 1).show();
				}
			}else{
				Toast.makeText(RegisterActivity.this, "与服务器通信失败",1).show();
			}
			break;
		}
	}
	private void handleReturnType(String returntype) {
		// TODO Auto-generated method stub
		int ReturnType=Integer.parseInt(returntype);		
		switch(ReturnType){
		case 1001:
			Toast.makeText(this,"注册成功", 1).show();
			Log.i("response", rttype);
			String sessionid=list.getSessionId();
			SharedPreferences sp=getSharedPreferences("wotingfm", Context.MODE_PRIVATE);
			Editor et=sp.edit();
			et.putString(StringConstant.ISLOGIN, "true");
			et.putString(StringConstant.SESSIONID, sessionid);
			et.putString(StringConstant.USERNAME,username );
			et.commit();
			setResult(1);
			finish();
			break;
		case 1002:
			Toast.makeText(this,"注册用户信息在平台内与其他账户名重复", 1).show();
			break;
		case 0000:
			Toast.makeText(this,"注册失败", 1).show();
			default:
			Toast.makeText(RegisterActivity.this, "网络连接失败，请检查网络是否正常", 1).show();
		}	
	}
}
