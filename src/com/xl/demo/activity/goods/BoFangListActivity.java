package com.xl.demo.activity.goods;


import com.xl.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class BoFangListActivity extends Activity implements OnClickListener{
	private LinearLayout head_left_btn;
	private LinearLayout lin_1;
	private LinearLayout lin_2;
	private LinearLayout lin_3;
	private LinearLayout lin_4;
	private LinearLayout lin_5;
	private LinearLayout lin_6;
	private LinearLayout lin_7;
	private LinearLayout lin_8;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bofanglist);
		lin_1 = (LinearLayout) findViewById(R.id.lin_1);
    	lin_2 = (LinearLayout) findViewById(R.id.lin_2);
    	lin_3 = (LinearLayout) findViewById(R.id.lin_3);
    	lin_4 = (LinearLayout) findViewById(R.id.lin_4);
    	lin_5 = (LinearLayout) findViewById(R.id.lin_5);
    	lin_6 = (LinearLayout) findViewById(R.id.lin_6);
    	lin_7 = (LinearLayout) findViewById(R.id.lin_7);
    	lin_8 = (LinearLayout) findViewById(R.id.lin_8);
    	lin_1.setOnClickListener(this);
    	lin_2.setOnClickListener(this);
    	lin_3.setOnClickListener(this);
    	lin_4.setOnClickListener(this);
    	lin_5.setOnClickListener(this);
    	lin_6.setOnClickListener(this);
    	lin_7.setOnClickListener(this);
    	lin_8.setOnClickListener(this);
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		head_left_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_1:
			startActivity(new Intent(this, BoFangActivity.class));
			break;
		case R.id.lin_2:
			startActivity(new Intent(this, BoFangActivity.class));
			break;
		case R.id.lin_3:
			startActivity(new Intent(this, BoFangActivity.class));
			break;
		case R.id.lin_4:
			startActivity(new Intent(this, BoFangActivity.class));
			break;
		case R.id.lin_5:
			startActivity(new Intent(this, BoFangActivity.class));
			break;
		case R.id.lin_6:
			startActivity(new Intent(this, BoFangActivity.class));
			break;
		case R.id.lin_7:
			startActivity(new Intent(this, BoFangActivity.class));
			break;
		case R.id.lin_8:
			startActivity(new Intent(this, BoFangActivity.class));
			break;
		

		default:
			break;
		}
	}



}
