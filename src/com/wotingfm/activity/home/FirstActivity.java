package com.wotingfm.activity.home;


import java.util.ArrayList;

import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DialogRecognitionListener;
import com.compdigitec.libvlcandroidsample.AudioActivity;
import com.compdigitec.libvlcandroidsample.VideoActivity;
//import com.weiny.MmsPlayerActivity;
import com.wotingfm.activity.play.BoFangActivity;
import com.wotingfm.utils.Config;
import com.wotingfm.utils.Constants;
import com.wotingfm.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
public class FirstActivity extends Activity {
	private LinearLayout lin_more;
	private LinearLayout lin_findvoice;
	 private int mCurrentTheme = Config.DIALOG_THEME;
	  private BaiduASRDigitalDialog mDialog = null;
	private EditText mResult;
	private DialogRecognitionListener mRecognitionListener;
	private LinearLayout lin_aaa;
	private LinearLayout lin_bbb;
	private View lin_ccc;
	private LinearLayout lin_ddd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);	
		lin_aaa = (LinearLayout) findViewById(R.id.lin_aaa);
		lin_aaa.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent = new Intent(FirstActivity.this,VideoActivity.class);      
				intent.putExtra("path", "mms://alive.rbc.cn/fm1039");  
				intent.putExtra("content", "北京交通广播");   
				startActivity(intent); 
			}
		});
		lin_bbb = (LinearLayout) findViewById(R.id.lin_bbb);
		lin_bbb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent1 = new Intent(FirstActivity.this,VideoActivity.class);      
				intent1.putExtra("path", "mms://alive.rbc.cn/fm974");  
				intent1.putExtra("content", "北京音乐广播");   
				startActivity(intent1); 
			}
		});
		lin_ccc = (LinearLayout) findViewById(R.id.lin_ccc);
		lin_ccc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent1 = new Intent(FirstActivity.this,AudioActivity.class);      
				intent1.putExtra("path", "http://192.168.1.113:8080/wt/asset/audio/cz_xyj_75.mp3");     
				intent1.putExtra("content", "西游记");    
				startActivity(intent1); 
			}
		});
		lin_ddd = (LinearLayout) findViewById(R.id.lin_ddd);
		lin_ddd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent1 = new Intent(FirstActivity.this,AudioActivity.class);      
				intent1.putExtra("path", "http://192.168.1.113:8080/wt/asset/audio/甜蜜蜜.mp3");     
				intent1.putExtra("content", "甜蜜蜜");    
				startActivity(intent1); 
			}
		});
		////////////////////
		
	     mResult = (EditText) findViewById(R.id.editText);
	        mRecognitionListener = new DialogRecognitionListener() {

	            @Override
	            public void onResults(Bundle results) {
	                ArrayList<String> rs = results != null ? results
	                        .getStringArrayList(RESULTS_RECOGNITION) : null;
	                if (rs != null && rs.size() > 0) {
	                    mResult.setText(rs.get(0));
	                }

	            }
	        };
		lin_more = (LinearLayout) findViewById(R.id.lin_more);
		lin_more.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FirstActivity.this, HomeActivity.class));
			}
		});
		lin_findvoice = (LinearLayout) findViewById(R.id.lin_findvoice);
		lin_findvoice.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//              if (mDialog == null || mCurrentTheme != Config.DIALOG_THEME) {
                mCurrentTheme = Config.DIALOG_THEME;
                if (mDialog != null) {
                    mDialog.dismiss();
                }
                Bundle params = new Bundle();
                params.putString(BaiduASRDigitalDialog.PARAM_API_KEY, Constants.API_KEY);
                params.putString(BaiduASRDigitalDialog.PARAM_SECRET_KEY, Constants.SECRET_KEY);
                params.putInt(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, Config.DIALOG_THEME);
                mDialog = new BaiduASRDigitalDialog(FirstActivity.this, params);
                mDialog.setDialogRecognitionListener(mRecognitionListener);
//            }
            mDialog.getParams().putInt(BaiduASRDigitalDialog.PARAM_PROP, Config.CURRENT_PROP);
            mDialog.getParams().putString(BaiduASRDigitalDialog.PARAM_LANGUAGE,
                    Config.getCurrentLanguage());
            Log.e("DEBUG", "Config.PLAY_START_SOUND = "+Config.PLAY_START_SOUND);
            mDialog.getParams().putBoolean(BaiduASRDigitalDialog.PARAM_START_TONE_ENABLE, Config.PLAY_START_SOUND);
            mDialog.getParams().putBoolean(BaiduASRDigitalDialog.PARAM_END_TONE_ENABLE, Config.PLAY_END_SOUND);
            mDialog.getParams().putBoolean(BaiduASRDigitalDialog.PARAM_TIPS_TONE_ENABLE, Config.DIALOG_TIPS_SOUND);
            mDialog.show();
			}
		});
	}



}
