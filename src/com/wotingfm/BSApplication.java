package com.wotingfm;


import com.wotingfm.IProcess.ICondition;
import com.wotingfm.receiver.NetWorkChangeReceiver;
import com.wotingfm.utils.CommonHelper;
import com.wotingfm.utils.PhoneMessage;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class BSApplication extends Application implements ICondition {

	public static Context mContext = null;
	private NetWorkChangeReceiver netWorkChangeReceiver = null;
	

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		BSApplication.mContext = getApplicationContext();

		
		PhoneMessage.getPhoneInfo(mContext);
		CommonHelper.checkNetworkStatus(mContext);
		this.registerNetWorkChangeReceiver(new NetWorkChangeReceiver(this));// 注册网络状态及返回键监听
		try {
			Class.forName("android.os.AsyncTask");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Context getContext() {
		return mContext;
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		//new DownLoadService(this, this, TaskConstant.TASK_ALLAPP_PAUSE_DOWNLOAD)
		//		.saveUnDownLoadResource();
		unRegisterNetWorkChangeReceiver(this.netWorkChangeReceiver);
		//unregisterReceiver(screenReceiver);
	}

	@Override
	public void doAfterNoNetWork() {}

	@Override
	public void doAfterOKNetWork() {
		
	}

	@Override
	public Handler getMyHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshUI(Message msg) {
		// TODO Auto-generated method stub

	}

	/*** 注册网络监听者 */
	private void registerNetWorkChangeReceiver(
			NetWorkChangeReceiver netWorkChangeReceiver) {
		this.netWorkChangeReceiver = netWorkChangeReceiver;
		IntentFilter filter = new IntentFilter();
		filter.addAction(NetWorkChangeReceiver.intentFilter);
		this.registerReceiver(netWorkChangeReceiver, filter);

	}

	/** 取消网络变化监听者 */
	private void unRegisterNetWorkChangeReceiver(
			NetWorkChangeReceiver netWorkChangeReceiver) {
		this.unregisterReceiver(netWorkChangeReceiver);
	}

	}

