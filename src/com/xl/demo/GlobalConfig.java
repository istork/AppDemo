package com.xl.demo;

import com.xl.demo.utils.PhoneMessage;

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
	
	public static final String baseUrl = "http://www.ym18.com/ceshi/";//正式
	
	public static final String goodsurl = baseUrl+"router/rest?";
	public static final String registerUrl = baseUrl+"wap/member/register.php?";
	public static final String loginUrl = baseUrl+"wap/member/login.php?";
	public static boolean islogin = false;
}
