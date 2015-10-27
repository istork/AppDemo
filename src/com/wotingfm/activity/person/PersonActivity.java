package com.wotingfm.activity.person;



import com.shenstec.activity.annotation.ViewInject;
import com.wotingfm.activity.login.login.activity.LoginActivity;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PersonActivity extends BaseActivity implements OnClickListener{
//	@ViewInject(id=R.id.head_left_btn,click=true)
//	private LinearLayout pubBtn;
	@ViewInject(id=R.id.tv_login,click=true)
	private TextView tv_login;
//	@ViewInject(id=R.id.head_right_btn,click=true)
//	private LinearLayout setBtn;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person);
		
	}
   
    @Override
    public void onClick(View v) {
    	// TODO Auto-generated method stub
    	super.onClick(v);
    	switch (v.getId()) {
		case R.id.tv_login:
			Intent intent = new Intent(this,LoginActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
    	
		
    }
    
    
	public void onPause() {
		super.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}


	long waitTime = 2000;  
	long touchTime = 0;  
	@Override  
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
		if(event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {  
			long currentTime = System.currentTimeMillis();  
			if((currentTime-touchTime)>=waitTime) {  
				ToastUtil.show_short(PersonActivity.this, "再按一次退出"); 
				touchTime = currentTime;  
			}else {  
				android.os.Process.killProcess(android.os.Process.myPid());
				finish();  
			}  
			return true;  
		}  
		return super.onKeyDown(keyCode, event);  
	}
}
