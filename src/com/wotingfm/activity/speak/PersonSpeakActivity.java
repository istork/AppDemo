package com.wotingfm.activity.speak;


import com.wotingfm.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class PersonSpeakActivity extends Activity {
	private LinearLayout head_left_btn;
	private LinearLayout lin_sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_speak);
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		head_left_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		lin_sp = (LinearLayout) findViewById(R.id.lin_sp);
		lin_sp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(PersonSpeakActivity.this, SpeakaActivity.class));
			}
		});
	}



}
