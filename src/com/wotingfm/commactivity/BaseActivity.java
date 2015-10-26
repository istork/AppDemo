package com.wotingfm.commactivity;
import com.wotingfm.IProcess.ICondition;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public abstract class BaseActivity extends com.shenstec.activity.BaseActivity implements ICondition{
	/**负责刷新主线程页面*/
	public Handler handler =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			// 调用子类的刷新
			
			BaseActivity.this.refreshUI(msg);
		}
	};
	
	
	@Override
	public void refreshUI(Message msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	/**有网络处理*/
	@Override
	public void doAfterNoNetWork() {
		
	}
	
	/**无网络处理*/
	@Override
	public void doAfterOKNetWork() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Handler getMyHandler() {
		// TODO Auto-generated method stub
		return this.handler;
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	public void changeDots(int listsize){
		
	}
	public void changeDots0(int listsize){
		
	}

}
