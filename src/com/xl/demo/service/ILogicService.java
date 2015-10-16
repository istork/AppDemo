package com.xl.demo.service;


import android.content.Context;

import com.xl.demo.IProcess.ICondition;

/**
 *  Function:
 *     业务逻辑类总接口
*/

public interface ILogicService {
	
	/***返回实现类的Context*/
	public Context getContext();
	/***返回实现类的ICondition*/
	public ICondition getICondition();
	/**返回实现类的任务id*/
	public int getTaskId();
	/**修改实现类的任务id*/
	public void setTaskId(int taskId);
	/**队返回资源进行处理逻辑*/
	public void process(Object object,int arg2);
	/**处理返回给后台的数据 子类可复写*/
	public void dealForBackGround(Object object);
	/**处理返回给前台的数据 子类可复写*/
	public void dealForUI(Object object,int arg2);
	/**返回当前任务触发请求的时间*/
	public int getRandomTime();
	
}
