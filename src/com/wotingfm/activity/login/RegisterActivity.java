package com.wotingfm.activity.login;



import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shenstec.activity.annotation.ViewInject;
import com.wotingfm.GlobalConfig;
import com.wotingfm.R;
import com.wotingfm.commactivity.BaseActivity;
import com.wotingfm.common.StringConstant;
import com.wotingfm.common.TaskConstant;
import com.wotingfm.model.UserInfo;
import com.wotingfm.response.RegisterResponse;
import com.wotingfm.service.LoginService;
import com.wotingfm.service.RegisterService;
import com.wotingfm.utils.ToastUtil;
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


	private UserInfo list;


	private String rttype;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_register);
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
		   
        	/*mEditTextName.setError("用户名不能为空");*/
        	mEditTextName.setError(Html.fromHtml("<font color=#ff0000>用户名不能为空</font>"));
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
    	ToastUtil.show_short(this, "提交数据中");
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
			int randomX = (int) System.currentTimeMillis();
			new RegisterService(this, this, TaskConstant.TASK_REGISTER).sendRegisterRequest(randomX, "", username, password, passwordcf);
		}else{
			ToastUtil.show_short(this, "网络失败，请检查网络");
		}
	}
	public void refreshUI(Message msg) {

		switch (msg.what) {
		case TaskConstant.TASK_REGISTER:
//			if(dialog!=null){
//				dialog.dismiss();
//			}
			if(msg.obj!=null && msg.obj instanceof RegisterResponse){
				RegisterResponse RegisterResponse=(RegisterResponse) msg.obj;
				try {
					 list = RegisterResponse.list;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rttype=list.getReturnType();
				String sessionid=list.getSessionId();
				SharedPreferences sp=getSharedPreferences("wotingfm", Context.MODE_PRIVATE);
				Editor et=sp.edit();
				et.putString(StringConstant.SESSIONID, sessionid);
				et.commit();
			}
			break;

		}
	}

	
}
