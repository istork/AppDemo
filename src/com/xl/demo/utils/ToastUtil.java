package com.xl.demo.utils;

import android.content.Context;
import android.widget.Toast;

/**toast提示工具类*/
public class ToastUtil {
	/**长时间提示*/
	public static void show_long(Context context, String content){
		Toast.makeText(context, content, Toast.LENGTH_LONG).show();
	}
	/**短时间提示*/
	public static void show_short(Context context, String content){
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}
}
