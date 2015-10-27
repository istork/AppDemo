package com.wotingfm.activity.interphone;


import com.wotingfm.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SpeakgroupActivity extends Activity {
	private LinearLayout head_left_btn;
	private LinearLayout head_right_btn;
	private LinearLayout lin_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		head_left_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		head_right_btn = (LinearLayout) findViewById(R.id.head_right_btn);
		head_right_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SpeakgroupActivity.this, SpeakAddGroupActivity.class));
			}
		});
		lin_1 = (LinearLayout) findViewById(R.id.lin_1);
		lin_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SpeakgroupActivity.this, SpeakActivity.class));
			}
		});
	}



}
