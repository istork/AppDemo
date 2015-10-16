package com.xl.demo.activity.goods;


import com.xl.demo.R;
import com.xl.demo.activity.goods.FMBoFangActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class FMbdOldListActivity extends Activity {
	private LinearLayout head_left_btn;
	private LinearLayout lin_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fm_bfoldlist);
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		head_left_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		lin_1 = (LinearLayout) findViewById(R.id.lin_1);
		lin_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FMbdOldListActivity.this, FMBoFangActivity.class));
			}
		});
	}



}
