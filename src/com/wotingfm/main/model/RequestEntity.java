package com.wotingfm.main.model;

import java.util.HashMap;

import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.GlobalConfig;

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
/*		String channelAndImei = GlobalConfig.REQUEST_CHANNELID
				+ PhoneMessage.channelId + GlobalConfig.REQUEST_IMEI
				+ PhoneMessage.imei + GlobalConfig.APP_VERSION
				+ PhoneMessage.appVersonName;*/
		switch (taskId) {
		case TaskConstant.TASK_REGISTER:
			url = GlobalConfig.registerUrl;
			break;
		case TaskConstant.TASK_LOGIN:
			url = GlobalConfig.loginUrl;
			break;
		case TaskConstant.Task_GetTalkPerson:
			url = GlobalConfig.gettalkpersonsurl;
			break;
		case TaskConstant.Task_Forget:
			url=GlobalConfig.forgetUrl;
			break;
		case TaskConstant.Task_Reset:
			url=GlobalConfig.ResetPassWordUrl;
			break;
		case TaskConstant.Task_CreatTalkGroup:
			url=GlobalConfig.creattalkgroupUrl;
			break;
		case TaskConstant.Task_TalkOldList:
			url=GlobalConfig.talkoldlistUrl;
			break;
		case TaskConstant.Task_TalkGroupList:
			url=GlobalConfig.talkgrouplistUrl;
			break;
		case TaskConstant.Task_GroupTalk:
			url=GlobalConfig.grouptalkUrl;
			break;
		}
		System.out.println("url = "+url);
		System.out.println("requestData = "+new String (requestData));
	}

}
