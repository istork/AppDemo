package com.wotingfm.main.commonactivity;

import com.wotingfm.main.IProcess.ICondition;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;


public class BaseFragment extends Fragment implements ICondition{

	@Override
	public void doAfterNoNetWork() {
		// TODO Auto-generated method stub
		
	}

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
	public void refreshUI(Message msg) {
		// TODO Auto-generated method stub
		
	}
	/**负责刷新主线程页面*/
	public Handler handler =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			// 调用子类的刷新
			
			BaseFragment.this.refreshUI(msg);
		}
	};
	
	
}
