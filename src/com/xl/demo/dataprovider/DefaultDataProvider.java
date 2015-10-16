package com.xl.demo.dataprovider;

import com.xl.demo.service.ILogicService;

import android.content.Context;

/**
 *  Function:
 *    IDataProvider子类
*/

public abstract class DefaultDataProvider implements IDataProvider{

	/**注册进来的业务逻辑类*/
	public  ILogicService  logicService;
	 
	/**上下文对象*/
	public  Context  context;
	
	public DefaultDataProvider(Context context,ILogicService logicService){
		this.logicService = logicService;
		this.context = context;
	
	}

	@Override
	public ILogicService getLogicService() {
		// TODO Auto-generated method stub
		return this.logicService;
	} 
	
}
