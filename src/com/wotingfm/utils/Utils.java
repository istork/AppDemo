package com.wotingfm.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;





import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.sax.StartElementListener;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.Time;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenstec.utils.DateUtils;
import com.shenstec.utils.file.FileManager;
import com.wotingfm.R;
import com.wotingfm.main.common.StringConstant;


public class Utils {

	public static List<String> getTagsList(String originalText) {
		if (originalText == null || originalText.equals("")) {
			return null;
		}
		List<String> tags = new ArrayList<String>();
		int indexOfComma = originalText.indexOf(',');
		String tag;
		while (indexOfComma != -1) {
			tag = originalText.substring(0, indexOfComma);
			tags.add(tag);

			originalText = originalText.substring(indexOfComma + 1);
			indexOfComma = originalText.indexOf(',');
		}

		tags.add(originalText);
		return tags;
	}

	public static String getLogText(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sp.getString("log_text", "");
	}

	public static void setLogText(Context context, String text) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString("log_text", text);
		editor.commit();
	}
	
	
	///获取SessionId
	public static String getSessionId(Context context){
		SharedPreferences sp = 
				context.getSharedPreferences("wotingfm", Context.MODE_PRIVATE);
	    String SessionId= sp.getString(StringConstant.SESSIONID, "0");
		return SessionId;
		
	}


		public static void getAddTime(){
			SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd    HH:mm:ss ");     
			Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间     
			String    addtime    =    formatter.format(curDate); 
	
		}
	/**
	 * 压缩图片
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while ( baos.toByteArray().length / 1024>80) {	//循环判断如果压缩后图片是否大于100kb,大于继续压缩	
			Log.e("Crash", "压缩前"+baos.toByteArray().length / 1024);
			options -= 10;//每次都减少10
			if(options<50){
				break;
			}else{
				baos.reset();//重置baos即清空baos
			}
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
			Log.e("Crash", "压缩后"+baos.toByteArray().length / 1024);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	public static Bitmap compressImage1(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 90;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			Log.e("Crash", "压缩前"+baos.toByteArray().length / 1024);
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
			Log.e("Crash", "压缩后"+baos.toByteArray().length / 1024);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	public static Bitmap decodeFile(File f) {
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 480;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE
						|| height_tmp / 2 < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	public static  int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);            
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
	public static String  saveBitmap(Context context,Bitmap bitmap) throws Exception{
		String savepath = FileManager.getImageSaveFilePath(context);
		FileManager.createDirectory(savepath);
		String fileName=System.currentTimeMillis()+".jpg";
		File file = new File(savepath,fileName);
		if (file != null && file.exists()){
			file.delete();
		}
		FileOutputStream out = new FileOutputStream(file);
		bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
		out.flush();
		out.close();
		return savepath+fileName;
	}
	 
	/* SDK 版本 */
	// String sdk = Build.VERSION.SDK;
	public static int sdk_int = Build.VERSION.SDK_INT;
	private static PendingIntent in;
	private static Class classnames;
	private static String urls;
	private static String kinds;


	/**
	 * TextView中的文字显示不同颜色的方法
	 */
	public static void showDifferentColor(TextView textview,CharSequence text,int color,int start,int end,int flags){
		SpannableStringBuilder style=new SpannableStringBuilder(text);
		style.setSpan(new ForegroundColorSpan(color),start,end,flags);
		textview.setText(style);
	}
	public static boolean  isPhoneNumber(String phone){
		boolean b = false;
		if(phone==null||phone.equals("")||!(phone.length()==11)){
		}else if(isNum(phone)){
			b = true;
		}
		return b;
	}
	public static boolean isNum(String str){		
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");	
	}
	public static boolean verifyLenght(String str){
		boolean b = false;
		if(str==null||str.equals("")){
			return b;
		}
		if(str.length()<6||str.length()>20){
			return b;
		}else{
			b = true;
		}
		return b;
	}

	

	
	





	public static Dialog Dialogph(Context ctx,String str,Dialog dialog){
		View dialog1=LayoutInflater.from(ctx).inflate(R.layout.dialog, null);	
		//		LinearLayout linear = (LinearLayout)dialog1.findViewById(R.id.main_dialog_layout);
		TextView text_wenzi = (TextView)dialog1.findViewById(R.id.text_wenzi);
		text_wenzi.setText(str);
		dialog = new Dialog(ctx);
		dialog.setContentView(dialog1);
		dialog.setCanceledOnTouchOutside(false);
		dialog.getWindow().setGravity(Gravity.CENTER);
		dialog.getWindow().setBackgroundDrawableResource(R.color.dialog);
		dialog.show();
//		 android:background="@drawable/dialog_ph"
		return dialog;
	}



}
