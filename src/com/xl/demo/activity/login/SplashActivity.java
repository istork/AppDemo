package com.xl.demo.activity.login;


import com.xl.demo.R;
import com.xl.demo.activity.main.MainActivity;
import com.xl.demo.receiver.FloatingWindowService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
//	private Context context;
	public static final int LOADFINISH = 1000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
//		context = SplashActivity.this;
		Intent show = new Intent(this, FloatingWindowService.class);
		show.putExtra(FloatingWindowService.OPERATION, FloatingWindowService.OPERATION_SHOW);
        startService(show);
		new Handler().postDelayed(new MyRunnable(), LOADFINISH);
	}

	

	class MyRunnable implements Runnable {

		@Override
		public void run() {
			SplashActivity.this.finish();
			startActivity(new Intent(SplashActivity.this, MainActivity.class));
		}

	}


}
