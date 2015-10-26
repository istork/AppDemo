package com.wotingfm.activity.home;


import java.util.ArrayList;




import com.wotingfm.adapter.MyFragmentPagerAdapter;
import com.wotingfm.commactivity.BaseFragmentActivity;
import com.wotingfm.R;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FMnewListActivity extends   BaseFragmentActivity implements
OnClickListener {
	private TextView view1;
	private TextView view2;
	private TextView view3;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private Context context;
	private LinearLayout head_left_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fm_new_list);
		context = getApplicationContext();
		InitTextView();
		InitViewPager();
	}

	private void InitTextView() {
		// TODO Auto-generated method stub
		view1 = (TextView) findViewById(R.id.tv_guid1);
		view2 = (TextView) findViewById(R.id.tv_guid2);
		view3 = (TextView) findViewById(R.id.tv_guid3);
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		head_left_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		view1.setOnClickListener(new txListener(0));
		view2.setOnClickListener(new txListener(1));
		view3.setOnClickListener(new txListener(2));
	}
	public class txListener implements View.OnClickListener {
		private int index = 1;

		public txListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mPager.setCurrentItem(index);
			if(index == 0){
				view1.setTextColor(context.getResources().getColor(R.color.orangered));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(index == 1){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.orangered));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(index == 2){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.orangered));
			}
		}
	}
	/*
	 * 初始化ViewPager
	 */
	public void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.viewpager);
		mPager.setOffscreenPageLimit(1);
		fragmentList = new ArrayList<Fragment>();
		Fragment btFragment = new FFragment();
		Fragment btFragment1 = new FFragment();
		Fragment btFragment2 = new FFragment();

		fragmentList.add(btFragment);
		fragmentList.add(btFragment1);
		fragmentList.add(btFragment2);
		mPager.setAdapter(new MyFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentList));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());// 页面变化时的监听�?
		mPager.setCurrentItem(1);// 设置当前显示标签页为第一�?
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {
		private int currIndex;

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			currIndex = arg0;
			if(currIndex == 0){
				view1.setTextColor(context.getResources().getColor(R.color.orangered));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(currIndex == 1){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.orangered));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(currIndex == 2){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.orangered));
			}
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
