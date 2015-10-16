package com.xl.demo.activity.goods;


import com.xl.demo.R;
import com.xl.demo.activity.home.FMnewListActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class FMBoFangActivity extends Activity {
	private LinearLayout head_left_btn;
	private LinearLayout lin_old;
	private LinearLayout lin_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fm_bofang);
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		lin_old = (LinearLayout) findViewById(R.id.lin_old);
		lin_list = (LinearLayout) findViewById(R.id.lin_list);
		lin_list.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FMBoFangActivity.this, FMnewListActivity.class));
			}
		});
		head_left_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		lin_old.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FMBoFangActivity.this, FMbdOldListActivity.class));
			}
		});
	}



}
