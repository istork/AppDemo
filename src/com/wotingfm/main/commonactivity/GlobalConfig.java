package com.wotingfm.main.commonactivity;

import com.wotingfm.utils.PhoneMessage;

public class GlobalConfig {

	/**渠道号前缀常量*/
	public static final String REQUEST_CHANNELID="channelId=";
	/**imei号前缀常量*/
	public static final String REQUEST_IMEI="&imei=";
	public static final String APP_VERSION="&version=";
	public static final int NETWORK_STATE_IDLE = -1;
	public static final int NETWORK_STATE_WIFI = 1;
	public static final int NETWORK_STATE_CMNET = 2;
	public static final int NETWORK_STATE_CMWAP = 3;
	public static final int NETWORK_STATE_CTWAP = 4;

	public static int CURRENT_NETWORK_STATE_TYPE = NETWORK_STATE_IDLE;

	public static int SDCARD_MEDIA_UNMOUNTED = -1;
	public static int SDCARD_MEDIA_MOUNTED = 0;
	public static int CURRENT_SDCARD_STATUS = SDCARD_MEDIA_UNMOUNTED;
	//时间超时
	public static int HTTP_CONNECTION_TIMEOUT = 60 * 1000;
	public static int HTTP_SO_TIMEOUT = 60 * 1000;
	public static int HTTP_SOCKET_BUFFER_SIZE = 8 * 1024;

	
	/**post方式请求*/
	public static final String POST="POST";
	/**get方式请求*/
	public static final String GET="GET";
	public static final String NAVIAGE_PACAGE_NAME = "com.yumi.kefu";
	

	public static final String channelAndImei = GlobalConfig.REQUEST_CHANNELID + PhoneMessage.channelId 
			+ GlobalConfig.REQUEST_IMEI	+ PhoneMessage.imei
			+ GlobalConfig.APP_VERSION	+ PhoneMessage.appVersonName;
	

	
	
	
	public static final String baseUrl = "http://192.168.1.108:808/";//测试电脑
//	public static final String baseUrl = "http://192.168.1.113:8080/";//测试服务器
	/**image请求路径前缀*/
	public static final String imageurl=baseUrl+"wt/";//服务器
	//注册
	public static final String registerUrl = baseUrl+"wt/passport/register.do?";
	//login
	public static final String loginUrl = baseUrl+"wt/passport/mlogin.do?";
	public static final String forgetUrl=baseUrl+"";
	public static final String ResetPassWordUrl=baseUrl+"";

	//对讲-小组联系人-fragment
	public static final String gettalkpersonsurl=baseUrl+"wt/passport/getFriendList.do?";
	//获取创建对讲小组的联系人
	public static final String creattalkgroupUrl=baseUrl+"wt/passport/getFriendList.do?";
	//通话中列表
	public static final String talkoldlistUrl=baseUrl+"wt/passport/getHistoryUG.do?";
	//对讲-小组列表
	public static final String talkgrouplistUrl=baseUrl+"wt/group/getGroupList.do";
	//对讲-创建对讲小组
	public static final String talkgroupcreatUrl=baseUrl+"wt/group/buildGroup.do?";
	//对讲-小组联系人
	public static final String grouptalkUrl=baseUrl+"wt/group/getGroupMembers.do?";
	//注销登录
	public static final String logoutUrl=baseUrl+"wt/passport/mlogout.do?";
	//启动页登录
	public static final String splashUrl=baseUrl+"wt/passport/entryApp.do?";
	public static boolean islogin = false;
}
