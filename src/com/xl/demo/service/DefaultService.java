package com.xl.demo.service;

import android.content.Context;
import android.os.Message;

import com.xl.demo.IProcess.ICondition;
import com.xl.demo.common.TaskConstant;

/***默认的业务逻辑实现类*/
public abstract class DefaultService implements ILogicService {

	public ICondition condition;
	public Context context;
	public int randomX = -1;
	/**任务id 默认无任务*/
	public int taskId=TaskConstant.TASK_NO;
	
	public DefaultService(Context context,ICondition condition,int taskId){
		this.context=context;
		this.condition=condition;
		this.taskId=taskId;
	}
	
	/**默认的返回数据处理*/
	public void process(Object object,int arg2) {
		
		dealForUI(object,arg2);
	}
	
	/**处理返回给后台的数据 默认的处理 子类可复写*/
	public void dealForBackGround(Object object){
		
	}
	
	/**处理返回给前台的数据 默认的处理  子类可复写*/
	public void dealForUI(Object object,int arg2){
		Message msg=this.condition.getMyHandler().obtainMessage();
		msg.what=this.taskId ;                 //任务标记
		msg.obj=object;//响应数据
		msg.arg2 = arg2;//携带类别ID
		if(randomX!=-1){
			msg.arg1 = randomX;
			randomX = -1;
		}
		this.condition.getMyHandler().sendMessageDelayed(msg,2);
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return this.context;
	}

	@Override
	public ICondition getICondition() {
		// TODO Auto-generated method stub
		return this.condition;
	}

	@Override
	public int getTaskId() {
		// TODO Auto-generated method stub
		return this.taskId;
	}

	@Override
	public void setTaskId(int taskId) {
		// TODO Auto-generated method stub
		this.taskId=taskId;
	}
	
	public abstract int getRandomTime();
}
