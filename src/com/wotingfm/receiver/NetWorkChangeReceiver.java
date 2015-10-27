package com.wotingfm.receiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wotingfm.main.IProcess.ICondition;
import com.wotingfm.utils.CommonHelper;
/**
 *  Function:
 *     网络变化监听者
*/
public class NetWorkChangeReceiver extends BroadcastReceiver {

	public static final String intentFilter="android.net.conn.CONNECTIVITY_CHANGE";
	
	public ICondition condition;
	private boolean isNetworkConnected = false;
	
	public NetWorkChangeReceiver(ICondition condition){
		this.condition=condition;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction();
		if(action != null)
		{
			/**没有网络*/
			if(CommonHelper.checkNetworkStatus(context) == -1)
			{
				doUnConnected();
				isNetworkConnected = false;
			}
			/**已经连上网*/
			else
			{
				if(!isNetworkConnected)
				{
					doConnected();
					isNetworkConnected = true;
				}
				
			}
		}
		
		
	}
	
	/**没连接上的处理*/
	public void doUnConnected(){
		this.condition.doAfterNoNetWork();
	}
	
	/**连接OK的处理*/
	public void doConnected(){
		this.condition.doAfterOKNetWork();
	}
	
}
