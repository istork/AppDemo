package com.wotingfm.utils;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.wotingfm.main.commonactivity.GlobalConfig;
public class CommonHelper {

	private static boolean isshowdownloaddialog_ = true;
	private static int screenWidth_ = 480;
	private static int screenHeight_ = 800;
	private static String cachepath_ = null;
	private static HashSet<String> cacheset_ = new HashSet<String>();
	private static PendingIntent floating_service_sender = null;
	private static String deviceid_ = null;


	/**
	 * 返回网络状态
	 * 
	 * @return 1为成功WiFi已连接，2为cmnet，3为cmwap，4为ctwap， -1为网络未连接
	 */
	public static int checkNetworkStatus(Context context) {
		ConnectivityManager mConnectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);// 上下文连接服务
		NetworkInfo info = mConnectivity.getActiveNetworkInfo();// 给定网络接口的状态类型
		if (info == null || !info.isAvailable()) {// 无网络判断
			return (GlobalConfig.CURRENT_NETWORK_STATE_TYPE = GlobalConfig.NETWORK_STATE_IDLE);
		}
		String typeName = info.getTypeName();
		if (typeName.equals("WIFI")) {
			return (GlobalConfig.CURRENT_NETWORK_STATE_TYPE = GlobalConfig.NETWORK_STATE_WIFI);
		}
		String extraName = info.getExtraInfo();
		if (extraName == null || extraName.trim().length() == 0) {
			String proxyHost = null;
			if (Build.VERSION.SDK_INT >= 13) {
				proxyHost = System.getProperties()
						.getProperty("http.proxyHost");
			} else {
				proxyHost = Proxy.getHost(context);
			}
			return (GlobalConfig.CURRENT_NETWORK_STATE_TYPE = (proxyHost == null ? GlobalConfig.NETWORK_STATE_CMNET
					: GlobalConfig.NETWORK_STATE_CMWAP));
		}

		if (extraName.equals("cmnet") || extraName.equals("3gnet")
				|| extraName.equals("uninet") || extraName.equals("ctnet")
				|| extraName.equals("ctnet:CDMA") || extraName.equals("CTC")) {
			return (GlobalConfig.CURRENT_NETWORK_STATE_TYPE = GlobalConfig.NETWORK_STATE_CMNET);
		} else if (extraName.equals("cmwap") || extraName.equals("3gwap")
				|| extraName.equals("uniwap")) {
			return (GlobalConfig.CURRENT_NETWORK_STATE_TYPE = GlobalConfig.NETWORK_STATE_CMWAP);
		} else if ("ctwap:CDMA".equals(extraName) || extraName.equals("ctwap")
				|| extraName.equals("#777")) {
			return (GlobalConfig.CURRENT_NETWORK_STATE_TYPE = GlobalConfig.NETWORK_STATE_CTWAP);
		}

		String proxyHost = null;
		if (Build.VERSION.SDK_INT >= 13) {
			proxyHost = System.getProperties().getProperty("http.proxyHost");
		} else {
			proxyHost = Proxy.getHost(context);
		}
		return (GlobalConfig.CURRENT_NETWORK_STATE_TYPE = (proxyHost == null ? GlobalConfig.NETWORK_STATE_CMNET
				: GlobalConfig.NETWORK_STATE_CMWAP));
	}

	/**
	 * 
	 * @param path
	 */
	public static void setCachePath(String path) {
		cachepath_ = path;
	}

	/**
	 * 
	 * @return
	 */
	public static String getCachePath() {
		return cachepath_;
	}

	/**
	 * 
	 * @return
	 */
	public static File getCacheFilePath() {
		File cachepath = new File(cachepath_ + "/cache/baoruan");
		if (!cachepath.exists()) {
			cachepath.mkdirs();
			String[] args1 = { "chmod", "777", cachepath.getAbsolutePath() };
			IOUtil.exec(args1);
		}
		return cachepath;
	}

	/**
	 * 
	 * @param name
	 */
	public static void setCacheFilePath(String name) {
		if (!cacheset_.contains(name)) {
			cacheset_.add(name);
		}
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isSaveCache(String name) {
		return cacheset_.contains(name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static boolean removeCache(String name) {
		return cacheset_.remove(name);
	}

	/**
	 * 
	 * @param height
	 */
	public static void setScreenHeight(int height) {
		screenHeight_ = height;
	}

	/**
	 * 
	 * @return
	 */
	public static int getScreenHeight() {
		return screenHeight_;
	}

	/**
	 * 
	 * @param width
	 */
	public static void setScreenWidth(int width) {
		screenWidth_ = width;
	}

	/**
	 * 
	 * @return
	 */
	public static int getScreenWidth() {
		return screenWidth_;
	}

	/**
	 * 
	 * @param value
	 */
	public static void setShowDownloadDialog(boolean value) {
		isshowdownloaddialog_ = value;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean getShowDownloadDialog() {
		return isshowdownloaddialog_;
	}

	/**
	 * 获取手机设备号IMEI
	 * 
	 * @return MD5校验值（32位字符串）
	 */
	public static String getDeviceId(Context mContext) {
		TelephonyManager TelephonyMgr = (TelephonyManager) mContext
				.getSystemService(Context.TELEPHONY_SERVICE);
		String m_szImei = TelephonyMgr.getDeviceId();
		if (m_szImei == null || "".equals(m_szImei)) {
			m_szImei = "0000000000000000";
		}
		StringBuffer res = new StringBuffer();
		res.append(m_szImei);
		res.append(Secure.getString(mContext.getContentResolver(),Secure.ANDROID_ID));
		WifiManager wm = (WifiManager) mContext
				.getSystemService(Context.WIFI_SERVICE);
		res.append(wm.getConnectionInfo().getMacAddress());
		BluetoothAdapter m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		res.append(m_BluetoothAdapter.getAddress());
		res.append(Build.BOARD).append(Build.BRAND).append(Build.CPU_ABI).append(Build.DEVICE);
		res.append(Build.DISPLAY).append(Build.HOST).append(Build.ID).append(Build.MANUFACTURER);
		res.append(Build.MODEL).append(Build.PRODUCT).append(Build.TAGS).append(Build.TYPE).append(Build.USER);
		return getMD5Str(res.toString());
	}
	
	/**
	 * 
	 * @param m_szLongID
	 * @return
	 */
	public static String getMD5Str(String m_szLongID) {
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
		// get md5 bytes
		byte p_md5Data[] = m.digest();
		// create a hex string
		String m_szUniqueID = new String();
		for (int i = 0; i < p_md5Data.length; i++) {
			int b = (0xFF & p_md5Data[i]);
			// if it is a single digit, make sure it have 0 in front (proper
			// padding)
			if (b <= 0xF)
				m_szUniqueID += "0";
			// add number to string
			m_szUniqueID += Integer.toHexString(b);
		}
		// hex string to uppercase
		m_szUniqueID = m_szUniqueID.toUpperCase();
		return m_szUniqueID;
	}

	/**
	 * Android唯一标识（唯一序列号）
	 * 
	 * @return MD5校验值（32位字符串）
	 */
	public static String getUniqueID(Context mContext) {
		String deviceid = (String) SharePreferenceManager
				.getSharePreferenceValue(mContext, PreferenceUtil.DEVICE,
						PreferenceUtil.DEVICE_ID, null);
		if (deviceid_ != null) {
			if (deviceid == null) {
				SharePreferenceManager.saveBatchSharedPreference(mContext,
						PreferenceUtil.DEVICE, PreferenceUtil.DEVICE_ID, deviceid_);
			}
			return deviceid_;
		}
		if (deviceid != null && deviceid.length() > 0) {
			deviceid_ = deviceid;
			return deviceid_;
		}
		deviceid_ = getDeviceId(mContext);
		SharePreferenceManager.saveBatchSharedPreference(mContext,
					PreferenceUtil.DEVICE, PreferenceUtil.DEVICE_ID, deviceid_);
		return deviceid_;
	}
}
