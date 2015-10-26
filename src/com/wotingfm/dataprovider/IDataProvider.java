package com.wotingfm.dataprovider;

import com.wotingfm.service.ILogicService;


/**数据提供者总接口 进行对DAO的crud 和 联网请求*/
public interface IDataProvider {
	/**接受上一层接口返回的值进行处理*/
	void process(Object object);
	/**获取监听者*/
	ILogicService getLogicService();
	/**解析响应的json*/
	Object parserJson2Obj(String jsonString);
}
