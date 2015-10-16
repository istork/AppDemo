package com.xl.demo.IProcess;

import android.os.Handler;
import android.os.Message;

/***
 * 接口 子类为界面 和 后台 
 *
 */
public interface ICondition {
	
	/**无网络操作*/
	public  void  doAfterNoNetWork();
	/**有网络操作*/
	public  void  doAfterOKNetWork();
	/***获取handler*/
	public Handler getMyHandler();
	/**刷新UI*/
	public void refreshUI(Message msg) ;
}
