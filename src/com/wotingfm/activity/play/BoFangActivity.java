package com.wotingfm.activity.play;


import com.weiny.MmsPlayerActivity;
import com.wotingfm.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class BoFangActivity extends Activity {
	private LinearLayout head_left_btn;
	private Intent intent;
	private LinearLayout lin_bf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bofang);
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		head_left_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		lin_bf = (LinearLayout) findViewById(R.id.lin_bf);
		lin_bf.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(BoFangActivity.this, MmsPlayerActivity.class);      
				intent.putExtra("path", "mms://alive.rbc.cn/fm1039");     
				 startActivity(intent); 
			}
		});
		
		
		
	}



}
