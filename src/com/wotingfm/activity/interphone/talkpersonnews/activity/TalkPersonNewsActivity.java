package com.wotingfm.activity.interphone.talkpersonnews.activity;


import com.wotingfm.R;
import com.wotingfm.activity.interphone.persontalk.activity.PersonTalkActivity;
import com.wotingfm.activity.interphone.talkperson.model.TalkPerson;
import com.wotingfm.activity.interphone.talkperson.model.TalkPersonInside;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TalkPersonNewsActivity extends Activity {
	private LinearLayout head_left_btn;
	private LinearLayout lin_sp;
	private TextView tv_name;
	private TalkPersonInside data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_talk_personnews);
		data=(TalkPersonInside)this.getIntent().getSerializableExtra("data");
		tv_name = (TextView) findViewById(R.id.tv_name);
		if(data.getUserName()==null||data.getUserName().equals("")){
			tv_name.setText("no name");
		}else{
			tv_name.setText(data.getUserName());
		}
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
				Intent intent=new Intent(TalkPersonNewsActivity.this,PersonTalkActivity.class);
				Bundle bundel=new Bundle();
				bundel.putString("type", "talkpersonnews");
				bundel.putSerializable("data", data);
				intent.putExtras(bundel);
				startActivity(intent);
			}
		});
	}



}
