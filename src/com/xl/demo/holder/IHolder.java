package com.xl.demo.holder;


/**
 *  Function:
 *     应用导航界面的ITEM holder事件监听
*/
public interface IHolder {
	
	/***根据状态 对相应的holder进行刷新*/
	void processStatus(int status);
	/***根据下载大小 对相应的holder进行刷新*/
	void processCurrentSize(int currentSize);
	/***处理资源的最大值*/
	void processMaxSize(int currentSize); 
}
