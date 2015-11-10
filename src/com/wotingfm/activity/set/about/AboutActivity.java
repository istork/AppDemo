package com.wotingfm.activity.set.about;


import com.shenstec.activity.BaseActivity;
import com.wotingfm.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
/*
 * 关于 
 * 辛龙
 */
public class AboutActivity extends BaseActivity implements OnClickListener {
	
	private LinearLayout head_left_btn;
	private AboutActivity context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		context=this;
		setview();//设置界面
		setlistener();//对界面设置监听
	}
	
	private void setview() {
		head_left_btn=(LinearLayout)findViewById(R.id.head_left_btn);//返回
	}
	
	private void setlistener() {
		head_left_btn.setOnClickListener(context);//返回
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.head_left_btn://返回
			finish();
			break;
		default:
			break;
		}
	}

}
