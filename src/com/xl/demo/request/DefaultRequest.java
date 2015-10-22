package com.xl.demo.request;

import android.os.Build;

import com.xl.demo.utils.PhoneMessage;

public class DefaultRequest {
	
	/**手机imei*/
	public String imei = PhoneMessage.imei;
	/**手机品牌*/
	public String machine = PhoneMessage.productor;
	/**手机型号*/
	public String type = PhoneMessage.model;
	/**分辨率*/
	public String screen = PhoneMessage.ScreenWidth+"x"+PhoneMessage.ScreenHeight;
	/**SDK版本*/
	public int versionSDK = Build.VERSION.SDK_INT;
}
