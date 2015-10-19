package com.xl.demo.activity.home;


import com.xl.demo.R;
import com.xl.demo.activity.goods.BoFangActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class FirstActivity extends Activity {
	private LinearLayout lin_more;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		lin_more = (LinearLayout) findViewById(R.id.lin_more);
		lin_more.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FirstActivity.this, HomeActivity.class));
			}
		});
	}



}
