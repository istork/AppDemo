package com.wotingfm.activity.login;



import com.shenstec.activity.annotation.ViewInject;
import com.wotingfm.commactivity.BaseActivity;
import com.wotingfm.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @author Administrator
 * @denglu
 */
public class RegisterActivity extends BaseActivity implements OnClickListener {
	@ViewInject(id=R.id.head_left_btn,click=true)
	private LinearLayout pubBtn;
	
	
	@ViewInject(id=R.id.btn_register,click=true)
	private Button btn_register;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_register);
    }
    
    
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.head_left_btn:
			finish();
			break;
		case R.id.btn_register:
			break;

		default:
			break;
		}
	}

	
}
