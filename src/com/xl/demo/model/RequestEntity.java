package com.xl.demo.model;

import java.util.HashMap;

import com.xl.demo.GlobalConfig;
import com.xl.demo.common.TaskConstant;
import com.xl.demo.utils.PhoneMessage;

import android.content.Context;

/**
 * 联网请求数据模型类，主要用于保存联网需要的参数
 */
public class RequestEntity {

	/** 联网url */
	public String url;
	/** 请求数据 */
	public byte[] requestData;
	/** Context实例 */
	public Context context;
	/** get参数 */
	public HashMap<String, String> requestMap;
	/** 请求类型 get或者post 默认post */
	public String requestType = GlobalConfig.POST;

	public int current_taskId = -1;

	public void switchUrl(final int taskId) {
		this.current_taskId = taskId;
		String channelAndImei = GlobalConfig.REQUEST_CHANNELID
				+ PhoneMessage.channelId + GlobalConfig.REQUEST_IMEI
				+ PhoneMessage.imei + GlobalConfig.APP_VERSION
				+ PhoneMessage.appVersonName;
		switch (taskId) {
		case TaskConstant.TASK_REGISTER:
			url = GlobalConfig.registerUrl+channelAndImei;
			break;
		case TaskConstant.TASK_LOGIN:
			url = GlobalConfig.loginUrl+channelAndImei;
			break;
		case TaskConstant.TASK_GOODS:
			url = GlobalConfig.goodsurl;
			break;
	
		}
		System.out.println("url = "+url);
		System.out.println("requestData = "+new String (requestData));
	}

}
