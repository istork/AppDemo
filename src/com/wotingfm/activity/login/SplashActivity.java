package com.wotingfm.activity.login;


import com.wotingfm.activity.main.MainActivity;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.receiver.FloatingWindowService;
import com.wotingfm.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;

public class SplashActivity extends Activity {
	//	private Context context;
	public static final int LOADFINISH = 1000;
	private SharedPreferences sharedPreferences;
	private String first;
	private View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = View.inflate(this, R.layout.activity_splash, null);
		setContentView(view);
		//		context = SplashActivity.this;
		sharedPreferences = this.getSharedPreferences("wotingfm",Context.MODE_PRIVATE);
		first = sharedPreferences.getString(StringConstant.FIRST, "0");//用户名，昵称
//		Intent show = new Intent(this, FloatingWindowService.class);
//		show.putExtra(FloatingWindowService.OPERATION, FloatingWindowService.OPERATION_SHOW);
//		startService(show);
		setview();
		//		new Handler().postDelayed(new MyRunnable(), LOADFINISH);
	}



	private void setview() {
		// TODO Auto-generated method stub
		// 设置动画效果是alpha，在anim目录下的alpha.xml文件中定义动画效果
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
		// 给view设置动画效果
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			// 这里监听动画结束的动作，在动画结束的时候开启一个线程，这个线程中绑定一个Handler,并
			// 在这个Handler中调用goHome方法，而通过postDelayed方法使这个方法延迟500毫秒执行，达到
			// 达到持续显示第一屏500毫秒的效果
			@Override
			public void onAnimationEnd(Animation arg0) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						//						Intent intent;
						//如果第一次，则进入引导页WelcomeActivity
						//						if (first) {
						////							intent = new Intent(MainActivity.this,
						////									WelcomeActivity.class);
						//						} else {
						//						}
						//						startActivity(intent);
						// 设置Activity的切换效果
						//						overridePendingTransition(R.anim.in_from_right,
						//								R.anim.out_to_left);
						if(first!=null&&first.equals("1")){
							startActivity(new Intent(SplashActivity.this, MainActivity.class));
						}else{
							startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
						}

						finish();
					}
				}, LOADFINISH);
			}
		});
	}
}



//	class MyRunnable implements Runnable {
//
//		@Override
//		public void run() {
//			SplashActivity.this.finish();
//			startActivity(new Intent(SplashActivity.this, MainActivity.class));
//		}
//
//	}


