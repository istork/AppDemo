package com.wotingfm.utils;
import com.wotingfm.R;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 			手机信息类
 * @author Administrator
 *
 */
public class PhoneMessage {
	
	/**
	 * 		手机屏幕的宽
	 */
	public static int ScreenWidth;
	/**
	 * 		手机屏幕的高
	 */
	public static int ScreenHeight;
	
	/**
	 * 		手机屏幕的高
	 */
	public static float density;
	/**
	 * 		手机屏幕的密度
	 */
	public static float densityDpi;
	/**
	 * 		本机的版本号
	 */
	public static String appVersonName = "";
	/**
	 * 		手机厂商
	 */
	public static String productor = "";
	/**
	 * 		手机型号
	 */
	public static String model = "";
	/**
	 * 		手机IMEI
	 */
	public static String imei = "";
	/**
	 * 		服务返回的机型id
	 */
	public static String modelId = "";
	/**
	 * 渠道号
	 */
	public static String channelId = "";
	public static int versionCode;
	
	public static void getPhoneInfo(Context context)
	{
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		PhoneMessage.ScreenWidth = display.getWidth();
		PhoneMessage.ScreenHeight = display.getHeight();
		DisplayMetrics dm  = context.getResources().getDisplayMetrics(); 
		PhoneMessage.density = dm.density;
		PhoneMessage.densityDpi = dm.densityDpi;
		getSysInfo(context);
		getAppInfo(context);
//		Loger.d("xu", "ScreenWidth="+PhoneMessage.ScreenWidth+"  ScreenHeight="+PhoneMessage.ScreenHeight+"  densityDpi="+PhoneMessage.densityDpi);
		Loger.w("chong", "ScreenWidth="+PhoneMessage.ScreenWidth+"  ScreenHeight="+PhoneMessage.ScreenHeight+"  densityDpi="+PhoneMessage.densityDpi);
	}
	
	/**
	 * 获得手机信息
	 */
	public static void getSysInfo(Context context) {
//		PhoneMessage.imei = CommonHelper.getIMEI(context);
//		PhoneMessage.imei = CommonHelper.getLocalMacAddress(context);
		PhoneMessage.imei = CommonHelper.getUniqueID(context);
		PhoneMessage.productor = Build.MANUFACTURER;
		PhoneMessage.model = Build.MODEL;
//		StringUtil.sdk_int = Build.VERSION.SDK_INT;
		
		Loger.d("xu", "imei="+PhoneMessage.imei+",model="+PhoneMessage.model+",productor="+PhoneMessage.productor);
	}
	
	public static void getAppInfo(Context context)
	{
		PhoneMessage.channelId = context.getResources().getString(R.string.channel_id);
		/**获取所装应用版本号*/
		PackageManager manager = context.getPackageManager();
		PackageInfo info;
		try {
			info = manager.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
			PhoneMessage.appVersonName = info.versionName;
			PhoneMessage.versionCode = info.versionCode;
//			PhoneConstant.app_version = "0"; 			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Loger.i("chong", "channelId="+PhoneMessage.channelId+",appversion="+PhoneMessage.appVersonName);
	}
}
