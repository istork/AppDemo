package com.xl.demo.commactivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import com.xl.demo.IProcess.ICondition;

public abstract class BaseFragmentActivity extends FragmentActivity implements ICondition{
	

protected boolean isondesk = true;
	
	/**负责刷新主线程页面*/
	public Handler handler =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 调用子类的刷新
			
			BaseFragmentActivity.this.refreshUI(msg);
		}
	};
	
	
	/**有网络处理*/
	@Override
	public void doAfterNoNetWork() {
	}
	
	/**无网络处理*/
	@Override
	public void doAfterOKNetWork() {
	}
	
	@Override
	public Handler getMyHandler() {
		return this.handler;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	public void changeDots(int listsize){
		
	}
	public void changeDots0(int listsize){
		
	}
	@Override
		protected void onCreate(Bundle arg0) {
			super.onCreate(arg0);
		}
	

}
