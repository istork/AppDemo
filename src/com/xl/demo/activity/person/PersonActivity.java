package com.xl.demo.activity.person;



import com.xl.demo.R;
import com.xl.demo.commactivity.BaseActivity;
import com.xl.demo.utils.ToastUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class PersonActivity extends BaseActivity{
//	@ViewInject(id=R.id.head_left_btn,click=true)
//	private LinearLayout pubBtn;
//	@ViewInject(id=R.id.head_name_tv)
//	private TextView titleTV;
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
