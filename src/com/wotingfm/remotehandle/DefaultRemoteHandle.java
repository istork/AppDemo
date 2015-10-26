package com.wotingfm.remotehandle;

import java.util.HashMap;

import org.json.JSONException;

import com.wotingfm.common.StatusConstant;
import com.wotingfm.dataprovider.IDataProvider;
import com.wotingfm.model.RemoteRM;
import com.wotingfm.model.RequestEntity;
import com.wotingfm.request.DefaultRequest;
import com.wotingfm.response.DefaultResponse;
import com.wotingfm.utils.Loger;

/**
 *  Function:
 *     
*/

public abstract class DefaultRemoteHandle implements IRemoteHandle {
	
	
	/**发起请求的任务ID*/
	public int taskId;
	/**联网类*/
	public RemoteRM remoteRM;
	/**请求内容对象*/
	public RequestEntity requestEntity;
	/**注册监听者*/
	public IDataProvider dataProvider;
	/**封装的请求对象*/
	public DefaultRequest requestParma;
	/**封装的响应对象*/
	public DefaultResponse responseParma;
	public HashMap<String,String> requestMap;
	/**请求的json*/
	public String jsonString;
	public DefaultRemoteHandle(IDataProvider dataProvider,DefaultRequest requestParma,String jsonString,int taskId){
		
		/**通过任务ID来切换请求的接口地址*/
		this.dataProvider=dataProvider;
		this.requestParma=requestParma;
		this.jsonString = jsonString;
		this.taskId = taskId;
		this.setResponseModel();
		prepare();
	}
	
	
	public DefaultRemoteHandle(IDataProvider dataProvider,DefaultRequest requestParma,String jsonString
			,int taskId,HashMap<String,String> requestMap){
		
		/**通过任务ID来切换请求的接口地址*/
		this.dataProvider=dataProvider;
		this.requestParma=requestParma;
		this.jsonString = jsonString;
		this.taskId = taskId;
		this.setResponseModel();
		this.requestMap = requestMap;
		prepare();
	}
	
	@Override
	public void prepare() {
		
		if ( null != remoteRM ){
			remoteRM.cancel();
		}
		remoteRM = new RemoteRM(this.getDataProvider().getLogicService().getContext(),this,responseParma);
		
		requestEntity=new RequestEntity();
//		requestEntity.requestName=this.name;
		requestEntity.context=this.dataProvider.getLogicService().getContext();
		requestEntity.requestData=this.parseRequsetData(this.jsonString);
		requestEntity.requestMap = requestMap;

		requestEntity.switchUrl(this.taskId);
	} 



	@Override
	public void cancel() {
		remoteRM.cancel();
	}



	@Override
	public IDataProvider getDataProvider() {
		// TODO Auto-generated method stub
		return this.dataProvider;
	}



	@Override
	public void start() {
		if ( remoteRM.busy() ) return ;		//如果正在联网,return
		remoteRM.requestRemote(requestEntity) ;
	}
	
	/***修改请求实体内的一些连接参数 请求方式 是否是安全验证 必须用在start()之前*/
	public IRemoteHandle modifyRequestEntity(String requestType,boolean  isSSL ){
		if(this.requestEntity!=null){
			this.requestEntity.requestType=requestType;
		}
		return this;
	}
	
	@Override
	public void process(Object object) {
		
		this.dataProvider.process(object);
	}
	@Override
	public byte[] parseRequsetData(String jsonString) {
		byte [] data = null;
		if(jsonString!=null){
//			Loger.i("request_json",jsonString==null?"jsonString is null....." :jsonString);
				data =  jsonString.getBytes();
		}
		else
		{
			data = null;
		}
		return data;
	}

	@Override
	public Object parseResponseData(byte[] data) throws JSONException {
		StatusConstant.isSendNetRequest = false;
		String jsonString = new String (data);
		Loger.i("response_json",jsonString==null?"jsonString is null....." :jsonString);
		return dataProvider.parserJson2Obj(jsonString);
	}



	
}
