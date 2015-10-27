package com.wotingfm.activity.main;


import com.wotingfm.activity.home.FirstActivity;
import com.wotingfm.activity.home.HomeActivity;
import com.wotingfm.activity.interphone.DuiJiangActivity;
import com.wotingfm.activity.interphone.SpeakSetActivity;
import com.wotingfm.activity.person.PersonActivity;
import com.wotingfm.activity.play.BoFangActivity;
import com.wotingfm.activity.set.SetActivity;
import com.wotingfm.utils.CommonHelper;
import com.wotingfm.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends TabActivity implements OnClickListener  {
	private TabHost tabHost;
	// 标记选项卡
	private LinearLayout lin1;
	private LinearLayout lin2;
	private LinearLayout lin3;
	private LinearLayout lin4;
	private LinearLayout lin5;
	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private ImageView image4;
	private ImageView image5;
	private TextView tv1;
	private TextView tv2;
	private TextView tv4;
	private TextView tv5;
	// 选中选项卡
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CommonHelper.checkNetworkStatus(this);//网络设置获取
		//设置界面
		tabHost = extracted();
		findview();
		tabHost.setCurrentTabByTag("shouye");
		tv1.setTextColor(getResources().getColor(R.color.orangered));
		tv2.setTextColor(getResources().getColor(R.color.beijing));
		tv4.setTextColor(getResources().getColor(R.color.beijing));
		tv5.setTextColor(getResources().getColor(R.color.beijing));
		image1.setImageResource(R.drawable.tab1_down);
		image2.setImageResource(R.drawable.tab2);
		image4.setImageResource(R.drawable.tab4);
		image5.setImageResource(R.drawable.tab5);
	}

	private void findview() {
		// TODO Auto-generated method stub
		lin1 = (LinearLayout) findViewById(R.id.main_lin_1);
		lin2 = (LinearLayout) findViewById(R.id.main_lin_2);
		lin3 = (LinearLayout) findViewById(R.id.main_lin_3);
		lin4 = (LinearLayout) findViewById(R.id.main_lin_4);
		lin5 = (LinearLayout) findViewById(R.id.main_lin_5);
		image1 = (ImageView) findViewById(R.id.main_image_1);
		image2 = (ImageView) findViewById(R.id.main_image_2);
		image3 = (ImageView) findViewById(R.id.main_image_3);
		image4 = (ImageView) findViewById(R.id.main_image_4);
		image5 = (ImageView) findViewById(R.id.main_image_5);
		tv1 = (TextView) findViewById(R.id.main_tv_1);
		tv2 = (TextView) findViewById(R.id.main_tv_2);
		tv4 = (TextView) findViewById(R.id.main_tv_4);
		tv5 = (TextView) findViewById(R.id.main_tv_5);
		lin1.setOnClickListener(this);
		lin2.setOnClickListener(this);
		lin3.setOnClickListener(this);
		lin4.setOnClickListener(this);
		lin5.setOnClickListener(this);
		/*
		 * 主页跳转的五个界面
		 */	 
		tabHost.addTab(tabHost.newTabSpec("shouye").setIndicator("shouye")
				.setContent(new Intent(this, FirstActivity.class)));//首页
		tabHost.addTab(tabHost.newTabSpec("duijiang").setIndicator("duijiang")
				.setContent(new Intent(this, DuiJiangActivity.class)));//对讲
//		tabHost.addTab(tabHost.newTabSpec("bofang").setIndicator("bofang")
//				.setContent(new Intent(this, BoFangActivity.class)));//播放
		tabHost.addTab(tabHost.newTabSpec("news").setIndicator("news")
				.setContent(new Intent(this, PersonActivity.class)));//消息
		tabHost.addTab(tabHost.newTabSpec("set").setIndicator("set")
				.setContent(new Intent(this, SetActivity.class)));//设置
	}
	private TabHost extracted() {
		return getTabHost();
	}
	//当有这个页面被遮盖时   结束这个页面
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	//	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.main_lin_1:
			tabHost.setCurrentTabByTag("shouye");
			tv1.setTextColor(getResources().getColor(R.color.orangered));
			tv2.setTextColor(getResources().getColor(R.color.beijing));
			tv4.setTextColor(getResources().getColor(R.color.beijing));
			tv5.setTextColor(getResources().getColor(R.color.beijing));
			image1.setImageResource(R.drawable.tab1_down);
			image2.setImageResource(R.drawable.tab2);
			image4.setImageResource(R.drawable.tab4);
			image5.setImageResource(R.drawable.tab5);
			break;
		case R.id.main_lin_2:
			tabHost.setCurrentTabByTag("duijiang");
			tv1.setTextColor(getResources().getColor(R.color.beijing));
			tv2.setTextColor(getResources().getColor(R.color.orangered));
			tv4.setTextColor(getResources().getColor(R.color.beijing));
			tv5.setTextColor(getResources().getColor(R.color.beijing));
			image1.setImageResource(R.drawable.tab1);
			image2.setImageResource(R.drawable.tab2_down);
			image4.setImageResource(R.drawable.tab4);
			image5.setImageResource(R.drawable.tab5);
			break;
		case R.id.main_lin_3:
//			tabHost.setCurrentTabByTag("bofang");
			
			
			
			
			startActivity(new Intent(this, BoFangActivity.class));
			tv1.setTextColor(getResources().getColor(R.color.beijing));
			tv2.setTextColor(getResources().getColor(R.color.beijing));
			tv4.setTextColor(getResources().getColor(R.color.beijing));
			tv5.setTextColor(getResources().getColor(R.color.beijing));
			image1.setImageResource(R.drawable.tab1);
			image2.setImageResource(R.drawable.tab2);
			image4.setImageResource(R.drawable.tab4);
			image5.setImageResource(R.drawable.tab5);
			break;
		case R.id.main_lin_4:
			tabHost.setCurrentTabByTag("news");
			tv1.setTextColor(getResources().getColor(R.color.beijing));
			tv2.setTextColor(getResources().getColor(R.color.beijing));
			tv4.setTextColor(getResources().getColor(R.color.orangered));
			tv5.setTextColor(getResources().getColor(R.color.beijing));
			image1.setImageResource(R.drawable.tab1);
			image2.setImageResource(R.drawable.tab2);
			image4.setImageResource(R.drawable.tab4_down);
			image5.setImageResource(R.drawable.tab5);
			break;
		case R.id.main_lin_5:
			tabHost.setCurrentTabByTag("set");
			tv1.setTextColor(getResources().getColor(R.color.beijing));
			tv2.setTextColor(getResources().getColor(R.color.beijing));
			tv4.setTextColor(getResources().getColor(R.color.beijing));
			tv5.setTextColor(getResources().getColor(R.color.orangered));
			image1.setImageResource(R.drawable.tab1);
			image2.setImageResource(R.drawable.tab2);
			image4.setImageResource(R.drawable.tab4);
			image5.setImageResource(R.drawable.tab5_down);
			break;
		default:
			break;
		}
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

}
