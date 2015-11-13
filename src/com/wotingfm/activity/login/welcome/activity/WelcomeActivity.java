package com.wotingfm.activity.login.welcome.activity;


import java.util.ArrayList;
import com.wotingfm.activity.login.login.activity.LoginActivity;
import com.wotingfm.activity.login.register.activity.RegisterActivity;
import com.wotingfm.activity.login.welcome.fragment.FifthGuideFragment;
import com.wotingfm.activity.login.welcome.fragment.FirstGuideFragment;
import com.wotingfm.activity.login.welcome.fragment.FourthGuideFragment;
import com.wotingfm.activity.login.welcome.fragment.SecondGuideFragment;
import com.wotingfm.activity.login.welcome.fragment.SixthGuideFragment;
import com.wotingfm.activity.login.welcome.fragment.ThirdGuideFragment;
import com.wotingfm.adapter.MyFragmentPagerAdapter;
import com.wotingfm.main.commonactivity.BaseFragmentActivity;
import com.wotingfm.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
/*
 * 对讲功能主页
 * 辛龙
 */
public class WelcomeActivity extends   BaseFragmentActivity implements
OnClickListener {
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private int bmpW;
	private int offset;
	private TextView tv_login;
	private TextView tv_register;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_guide);
		context = getApplicationContext();
		InitViewPager();
		 tv_login = (TextView) findViewById(R.id.tv_login);
	        tv_register = (TextView) findViewById(R.id.tv_register);
	        tv_login.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
				}
			});
	        tv_register.setOnClickListener(new OnClickListener() {
	        	
	        	@Override
	        	public void onClick(View v) {
	        		// TODO Auto-generated method stub
	        		Intent intent = new Intent(WelcomeActivity.this,RegisterActivity.class);
	    			startActivity(intent);
	        	}
	        });
	}

	/*
	 * 初始化ViewPager
	 */
	public void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.viewpager);
		mPager.setOffscreenPageLimit(1);
		fragmentList = new ArrayList<Fragment>();
		Fragment btFragment1 = new FirstGuideFragment();
		Fragment btFragment2 = new SecondGuideFragment();
		Fragment btFragment3 = new ThirdGuideFragment();
		Fragment btFragment4 = new FourthGuideFragment();
		Fragment btFragment5= new FifthGuideFragment();
		Fragment btFragment6 = new SixthGuideFragment();
		fragmentList.add(btFragment1);
		fragmentList.add(btFragment2);
		fragmentList.add(btFragment3);
		fragmentList.add(btFragment4);
		fragmentList.add(btFragment5);
		fragmentList.add(btFragment6);
		mPager.setAdapter(new MyFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentList));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());// 页面变化时的监听�?
		mPager.setCurrentItem(0);// 设置当前显示标签页为第一�?
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		private int one = offset * 2+ bmpW;// 两个相邻页面的偏移量
		private int currIndex;

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			Animation animation = new TranslateAnimation(currIndex * one, arg0
					* one, 0, 0);// 平移动画
			currIndex = arg0;
			animation.setFillAfter(true);// 动画终止时停留在最后一帧，不然会回到没有执行前的状态
			animation.setDuration(200);// 动画持续时间0.2秒
		}
	}

	@Override
	public void refreshUI(Message msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


}
