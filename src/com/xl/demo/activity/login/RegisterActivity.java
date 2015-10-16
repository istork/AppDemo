//package com.xl.demo.activity.login;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.xl.demo.GlobalConfig;
//import com.xl.demo.R;
//import com.xl.demo.IProcess.ICondition;
//import com.xl.demo.commactivity.BaseActivity;
//import com.xl.demo.common.StringConstant;
//import com.xl.demo.common.TaskConstant;
//import com.xl.demo.response.RegisterResponse;
//import com.xl.demo.service.RegisterService;
//import com.xl.demo.utils.ToastUtil;
//
//public class RegisterActivity extends BaseActivity implements OnClickListener ,ICondition{
//
//	private EditText editText_phone;
//	private EditText editText_password;
//	private EditText editText_conferm;
//	private EditText editText_name;
//	private Button btn_register;
//	private SharedPreferences sharedPreferences;
//	private ProgressDialog registerDialog;
//	private String phone;
//	private String name;
//	private boolean isRegisterSuccess = false;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_register);
//		sharedPreferences = this.getSharedPreferences("yumi",Context.MODE_PRIVATE);
//		findviews();
//		setlistener();
//	}
//	private void findviews() {
//		editText_phone = (EditText) findViewById(R.id.edittext_phone);
//		editText_password = (EditText) findViewById(R.id.edittext_password);
//		editText_conferm = (EditText) findViewById(R.id.edittext_conferm);
//		editText_name = (EditText) findViewById(R.id.edittext_name);
//		btn_register = (Button) findViewById(R.id.btn_register);
//	}
//	private void setlistener() {
//		btn_register.setOnClickListener(this);
//	}
//
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.btn_register:
//			register();
//			break;
//		default:
//			break;
//		}
//	}
//	private void register() {
//		phone = editText_phone.getText().toString().trim();
//		if(phone==null || phone.equals("")){
//			ToastUtil.show_short(this, "手机不能为空");
//			return;
//		}
//		name = editText_name.getText().toString().trim();
//		if(name==null || name.equals("")){
//			ToastUtil.show_short(this, "姓名不能为空");
//			return;
//		}
//		
//		if(!(phone.length()==11)){
//			ToastUtil.show_short(this, "手机号长度不对");
//			return;
//		}
//		if(!phone.startsWith("1")){
//			ToastUtil.show_short(this, "手机号输入有误");
//			return;
//		}
//		String password = editText_password.getText().toString().trim();
//		String conferm = editText_conferm.getText().toString().trim();
//		if(password==null || password.equals("")){
//			ToastUtil.show_short(this, "密码不能为空");
//			return;
//		}
//		if(conferm==null || conferm.equals("")){
//			ToastUtil.show_short(this, "确认密码不能为空");
//			return;
//		}
//		if(!password.equals(conferm)){
//			ToastUtil.show_short(this, "两次输入密码不一致");
//			return;
//		}
//		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
//			registerDialog = new ProgressDialog(this);
//			if(registerDialog==null){
//				registerDialog.setMessage("正在注册...");
//			}
//			registerDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//			registerDialog.show();
//			int randomX = (int) System.currentTimeMillis();
//			new RegisterService(this, this, TaskConstant.TASK_REGISTER).sendRegisterRequest(randomX,phone,name,password,conferm);
//		}else{
//			ToastUtil.show_short(this, "联网失败，请检查网络");
//		}
//	}
//	@Override
//	public void refreshUI(Message msg) {
//		switch (msg.what) {
//		case TaskConstant.TASK_REGISTER:
//			dealRegister(msg);
//			break;
//
//		default:
//			break;
//		}
//	}
//	private void dealRegister(Message msg) {
//		if(registerDialog!=null){
//			registerDialog.dismiss();
//		}
//		if(msg.obj!=null){
//			if(msg.obj instanceof RegisterResponse){
//				RegisterResponse response = (RegisterResponse) msg.obj;
//				String userid = response.userid;
//				if(userid!=null){
//					int id = Integer.parseInt(userid);
//					if(id==-1){
//						ToastUtil.show_short(this, "手机号已注册");
//					}else if(id>0){
//						Editor edit = sharedPreferences.edit();
//						edit.putString(StringConstant.USERID, userid);
//						edit.putString(StringConstant.USERNAME, phone);
//						edit.putBoolean(StringConstant.ISLOGIN, true);
//						GlobalConfig.USERID = userid;
//						GlobalConfig.USENAME = phone;
//						GlobalConfig.islogin=true;
//						edit.commit();
//						ToastUtil.show_short(this, "注册成功");
//						isRegisterSuccess = true;
//						setResult(200);
//						this.finish();
//					}else{
//						if(response.REV==null||!response.REV.equals("true")){
//							ToastUtil.show_short(this, "注册失败");
//						}
//					}
//				}else{
//					ToastUtil.show_short(this, "注册失败");
//				}
//			}else{
//				ToastUtil.show_short(this, "注册失败");
//			}
//		}else{
//			ToastUtil.show_short(this, "注册失败");
//		}
//	}
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//	}
//	@Override
//	public void onBackPressed() {
//		if(isRegisterSuccess){
//			setResult(200);
//		}else{
//			setResult(201);
//		}
//		this.finish();
//		super.onBackPressed();
//	}
//}
