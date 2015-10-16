package com.xl.demo.remotehandle;

import org.json.JSONException;

import com.xl.demo.dataprovider.IDataProvider;

/**
 *  Function:
 *     远程访问 + 数据转化 接口
*/

public interface IRemoteHandle {
	
	/**解析请求数据*/
	byte[] parseRequsetData(String jsonString);
	/**解析返回数据*/
	Object parseResponseData(byte[] data)throws JSONException;
	/**准备工作*/
	void prepare();
	/**发送请求*/
	void start ();
	/**取消请求*/
	void cancel();
	/**获取监听者*/
	IDataProvider getDataProvider();
	/**设置请求返回数据流要解析成的格式*/
	void setResponseModel();
	/**对上层传输下来的数据的处理*/
	void process(Object object);
}
