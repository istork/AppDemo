package com.xl.demo.activity.home;


import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.xl.demo.R;
import com.xl.demo.adapter.MyFragmentPagerAdapter;
import com.xl.demo.commactivity.BaseActivity;
import com.xl.demo.commactivity.BaseFragmentActivity;
import com.xl.demo.utils.PhoneMessage;
import com.xl.demo.utils.ToastUtil;

import android.content.Context;
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

public class HomeActivity extends   BaseFragmentActivity implements
OnClickListener {
	private TextView view1;
	private TextView view2;
	private TextView view3;
	private TextView view4;
	private TextView view5;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private ImageView image;
	private LayoutParams lp;
	private int bmpW;
	private Context context;
	private int offset;
	private LinearLayout lin_find;
	private LinearLayout head_left_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		context = getApplicationContext();
		InitTextView();
		InitImage();
		InitViewPager();
	}

	private void InitTextView() {
		// TODO Auto-generated method stub
		view1 = (TextView) findViewById(R.id.tv_guid1);
		view2 = (TextView) findViewById(R.id.tv_guid2);
		view3 = (TextView) findViewById(R.id.tv_guid3);
		view4 = (TextView) findViewById(R.id.tv_guid4);
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		head_left_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();
			}
		});
		lin_find = (LinearLayout) findViewById(R.id.lin_find);
		lin_find.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtil.show_short(HomeActivity.this, "find");
			}
		});
		view5 = (TextView) findViewById(R.id.tv_guid5);
		view1.setOnClickListener(new txListener(0));
		view2.setOnClickListener(new txListener(1));
		view3.setOnClickListener(new txListener(2));
		view4.setOnClickListener(new txListener(3));
		view5.setOnClickListener(new txListener(4));
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
				view1.setTextColor(context.getResources().getColor(R.color.orangered));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
				view4.setTextColor(context.getResources().getColor(R.color.s_green));
				view5.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(index == 1){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.orangered));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
				view4.setTextColor(context.getResources().getColor(R.color.s_green));
				view5.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(index == 2){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.orangered));
				view4.setTextColor(context.getResources().getColor(R.color.s_green));
				view5.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(index == 3){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
				view4.setTextColor(context.getResources().getColor(R.color.orangered));
				view5.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(index == 4){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
				view4.setTextColor(context.getResources().getColor(R.color.s_green));
				view5.setTextColor(context.getResources().getColor(R.color.orangered));
			}
		}
	}
	/*
	 * 初始化图片的位移像素
	 */
	public void InitImage() {
		image = (ImageView) findViewById(R.id.cursor);
		lp=image.getLayoutParams();
		lp.width= (PhoneMessage.ScreenWidth/5);
		image.setLayoutParams(lp);
		bmpW = BitmapFactory.decodeResource(getResources(),
				R.drawable.bg_recodingtype_label_on).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW/5-bmpW) / 2;

		// imgageview设置平移，使下划线平移到初始位置（平移一个offset�?
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		image.setImageMatrix(matrix);
	}
	/*
	 * 初始化ViewPager
	 */
	public void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.viewpager);
		mPager.setOffscreenPageLimit(1);
		fragmentList = new ArrayList<Fragment>();
		Fragment btFragment = new AFragment();
		Fragment btFragment1 = new BFragment();
		Fragment btFragment2 = new CFragment();
		Fragment btFragment3 = new DFragment();
		Fragment btFragment4 = new EFragment();

		fragmentList.add(btFragment);
		fragmentList.add(btFragment1);
		fragmentList.add(btFragment2);
		fragmentList.add(btFragment3);
		fragmentList.add(btFragment4);
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
			image.startAnimation(animation);// 是用ImageView来显示动画的
			if(currIndex == 0){
				view1.setTextColor(context.getResources().getColor(R.color.orangered));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
				view4.setTextColor(context.getResources().getColor(R.color.s_green));
				view5.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(currIndex == 1){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.orangered));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
				view4.setTextColor(context.getResources().getColor(R.color.s_green));
				view5.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(currIndex == 2){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.orangered));
				view4.setTextColor(context.getResources().getColor(R.color.s_green));
				view5.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(currIndex == 3){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
				view4.setTextColor(context.getResources().getColor(R.color.orangered));
				view5.setTextColor(context.getResources().getColor(R.color.s_green));
			}else if(currIndex == 4){
				view1.setTextColor(context.getResources().getColor(R.color.s_green));
				view2.setTextColor(context.getResources().getColor(R.color.s_green));
				view3.setTextColor(context.getResources().getColor(R.color.s_green));
				view4.setTextColor(context.getResources().getColor(R.color.s_green));
				view5.setTextColor(context.getResources().getColor(R.color.orangered));
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
