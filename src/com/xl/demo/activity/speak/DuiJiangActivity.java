package com.xl.demo.activity.speak;


import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.xl.demo.R;
import com.xl.demo.adapter.MyFragmentPagerAdapter;
import com.xl.demo.commactivity.BaseActivity;
import com.xl.demo.commactivity.BaseFragmentActivity;
import com.xl.demo.utils.PhoneMessage;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DuiJiangActivity extends   BaseFragmentActivity implements
OnClickListener {
	private TextView view1;
	private TextView view2;
	private TextView view3;
	private TextView view4;
	private TextView view5;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private LayoutParams lp;
	private int bmpW;
	private Context context;
	private int offset;
	private LinearLayout lin_1;
	private LinearLayout lin_2;
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duijiang);
		context = getApplicationContext();
		type=1;
		
		InitTextView();
		InitViewPager();
		lin_1.setVisibility(View.INVISIBLE);
	}


	private void InitTextView() {
		// TODO Auto-generated method stub
		lin_1 = (LinearLayout) findViewById(R.id.lin_1);
		lin_2 = (LinearLayout) findViewById(R.id.lin_2);
		view1 = (TextView) findViewById(R.id.tv_guid1);
		view2 = (TextView) findViewById(R.id.tv_guid2);
		view3 = (TextView) findViewById(R.id.tv_guid3);
		view1.setOnClickListener(new txListener(0));
		view2.setOnClickListener(new txListener(1));
		view3.setOnClickListener(new txListener(2));
		lin_1.setOnClickListener(this);
		lin_2.setOnClickListener(this);
	}
	public class txListener implements View.OnClickListener {
		private int index = 0;

		public txListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mPager.setCurrentItem(index);
			if(index == 0){
				type=1;
				lin_1.setVisibility(View.INVISIBLE);
				view1.setTextColor(context.getResources().getColor(R.color.orangered));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(index == 1){
				type=2;
				lin_1.setVisibility(View.VISIBLE);
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.orangered));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(index == 2){
				type=3;
				lin_1.setVisibility(View.VISIBLE);
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
		Fragment btFragment = new CCFragment();
		Fragment btFragment1 = new BBFragment();
		Fragment btFragment2 = new AAFragment();

		fragmentList.add(btFragment);
		fragmentList.add(btFragment1);
		fragmentList.add(btFragment2);
		mPager.setAdapter(new MyFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentList));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());// 页面变化时的监听�?
		mPager.setCurrentItem(0);// 设置当前显示标签页为第一�?
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		private int one = offset * 2+ bmpW;// 两个相邻页面的偏移量
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
			Animation animation = new TranslateAnimation(currIndex * one, arg0
					* one, 0, 0);// 平移动画
			currIndex = arg0;
			animation.setFillAfter(true);// 动画终止时停留在最后一帧，不然会回到没有执行前的状态
			animation.setDuration(200);// 动画持续时间0.2秒
			if(currIndex == 0){
				type=1;
				lin_1.setVisibility(View.INVISIBLE);
				view1.setTextColor(context.getResources().getColor(R.color.orangered));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(currIndex == 1){
				type=2;
				lin_1.setVisibility(View.VISIBLE);
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.orangered));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(currIndex == 2){
				type=3;
				lin_1.setVisibility(View.VISIBLE);
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
		switch (v.getId()) {
		case R.id.lin_1:
			if(type==3){
				startActivity(new Intent(context, SpeakAddActivity.class));
			}else if(type==2){
				startActivity(new Intent(context, SpeakAddGroupActivity.class));
			}

			break;
		case R.id.lin_2:
			startActivity(new Intent(context, SpeakSetActivity.class));
			break;

		default:
			break;
		}
	}









}
