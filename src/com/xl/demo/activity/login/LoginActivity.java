package com.xl.demo.activity.login;


import java.util.Map;
import java.util.Map.Entry;
import com.shenstec.activity.annotation.ViewInject;
import com.xl.demo.R;
import com.xl.demo.activity.main.MainActivity;
import com.xl.demo.commactivity.BaseActivity;
import com.xl.demo.common.StringConstant;

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
	@ViewInject(id=R.id.head_name_tv)
	private TextView titleTV;
	@ViewInject(id=R.id.head_right_btn,click=true)
	private LinearLayout setBtn;
	
	@ViewInject(id=R.id.lin_login,click=true)
	private LinearLayout lin_login;
	@ViewInject(id=R.id.lin_quxiao,click=true)
	private LinearLayout lin_quxiao;
	private Activity	activity = LoginActivity.this;
	private SharedPreferences sharedPreferences;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_login);
    	sharedPreferences = this.getSharedPreferences("xldemo",Context.MODE_PRIVATE);
    	titleTV.setText("登录");
		pubBtn.setVisibility(View.INVISIBLE);
		setBtn.setVisibility(View.INVISIBLE);
    }
    
    
//    private void initView(){
//    	lin_login = (LinearLayout)findViewById(R.id.lin_login);
//    	lin_quxiao = (LinearLayout)findViewById(R.id.lin_quxiao);
//    	lin_login.setOnClickListener(this);
//	}
    
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.head_left_btn:
			
			break;
		case R.id.lin_login:
//			service = AlibabaSDK.getService(LoginService.class); 
//			Toast.makeText(activity, "我被点了", 0).show();
			/**
			*showLogin() 功能描述：
			*唤起官方登录授权页面
			*@param activity
			*@param loginCallback
			*登录授权回调，<@link LoginCallback> 必填 */
//			AlibabaSDK.getService(LoginService.class).showLogin(activity, new InternalLoginCallBack());
			break;
		case R.id.lin_quxiao:
			break;

		default:
			break;
		}
	}
//	private class InternalLoginCallBack implements LoginCallback
//	{

		/**
		*登录授权失败之后回调
		*@param code
		*错误码。eg. 10003
		*@param msg
		*错误信息，eg. USER_CANCEL
		* 在LoginCallback的onFailure中code表示用户的操作状态，code为10003表示用户触发了顶部或者物理键中的“返回”操作;
		* 其他的code值表示系统异常，可以协助开发者做一些判断。
		*/
//		@Override
//		public void onFailure(int code, String message)
//		{
//			// TODO Auto-generated method stub
//			Toast.makeText(activity, "授权失败---errorcode:" + code, Toast.LENGTH_SHORT).show();
//		}
		/**
		*登录授权成功之后回调
		*@param session 登录用户信息对象，<@link Session>
		*/
//		public void onSuccess(com.alibaba.sdk.android.session.model.Session session)
//		{
//			 Toast.makeText(
//					 LoginActivity.this,
//	                    "授权成功" + session.getUserId() + session.getUser().nick + session.isLogin() + session.getLoginTime()
//	                            + session.getUser().avatarUrl, Toast.LENGTH_SHORT).show();
//			    String userid  = session.getUserId();
//			    User user = session.getUser();
//			    Boolean islogin = session.isLogin();
//			    Long longtime = session.getLoginTime();
//			    String avatarurl = session.getUser().avatarUrl;
//			    String islogin_s;
//			    if(islogin){
//			    	islogin_s = "1";
//			    }else{
//			    	islogin_s = "2";
//			    }
//			    Editor edit = sharedPreferences.edit();
//				edit.putString(StringConstant.USERID,userid);
//				edit.putString(StringConstant.USERNAME,session.getUser().nick);
//				edit.putString(StringConstant.ISLOGIN,islogin_s);
//				edit.putLong(StringConstant.LONGTIME,longtime);
//				edit.putString(StringConstant.AVATARURL,avatarurl);
//				edit.commit();
//				
//				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//				startActivity(intent);
//				
//				
//	            CookieManager.getInstance().removeAllCookie();
//	            CookieSyncManager.getInstance().sync();
//	            Map<String, String[]> m = WebViewActivitySupport.getInstance().getCookies();
//	            for (Entry<String, String[]> e : m.entrySet()) {
//	                for (String s : e.getValue()) {
//	                    CookieManager.getInstance().setCookie(e.getKey(), s);
//	                    System.out.println("key: " + e.getKey() + " value: " + s);
//	                }
//	            }
//	            CookieSyncManager.getInstance().sync();
//	            System.out.println("------------" + CookieManager.getInstance().getCookie("http://taobao.com"));
//		}
		
		
//	}
//	@SuppressWarnings("unused")
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		CallbackContext.onActivityResult(requestCode, resultCode, data);
//	}

	
}
