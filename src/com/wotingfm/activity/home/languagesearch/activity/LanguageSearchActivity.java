package com.wotingfm.activity.home.languagesearch.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.compdigitec.libvlcandroidsample.AudioActivity;
import com.compdigitec.libvlcandroidsample.VideoActivity;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.wotingfm.activity.home.HomeActivity;
import com.wotingfm.activity.home.languagesearch.adapter.LanguageSearchAdapter;
import com.wotingfm.activity.home.languagesearch.adapter.LanguageSearchAdapter.OnListener;
import com.wotingfm.activity.home.languagesearch.model.LanguageSearchInside;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.activity.person.PersonActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
//import com.weiny.MmsPlayerActivity;
import com.wotingfm.utils.Constants;
import com.wotingfm.utils.JsonParser;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;
import com.wotingfm.widgetui.CircleWaveView;
import com.wotingfm.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 语音搜索界面
 * 辛龙
 */

public class LanguageSearchActivity extends Activity implements OnClickListener{
	private LinearLayout lin_more;
	private LinearLayout lin_findvoice;
	private LinearLayout lin_left;
	private LinearLayout lin_right;
	private ImageView imageView_stop;
	private ListView listView;
	private LanguageSearchActivity context;
	private Dialog dialog;
	private ArrayList<LanguageSearchInside> ceshilist;
	private LanguageSearchInside ceshilist1;
	private LanguageSearchAdapter adapter;
	private ImageView image;
	private CircleWaveView circle_images;
	private SpeechRecognizer mIat;
	private EditText tv_voice;
	// 用HashMap存储听写结果
		private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_language_search);
		context=this;
		//初始化语音配置对象
		SpeechUtility.createUtility(context, SpeechConstant.APPID +"=56275014");
		//创建SpeechRecognizer对象，第二个参数：本地听写时传InitListener
		mIat= SpeechRecognizer.createRecognizer(this, null);
		setview();//设置界面
		setlistener();//设置监听
		//测试代码
		setdate();

		/*
		 * 真实代码
		 */
		//		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
		//			dialog = Utils.Dialogph(context, "通讯中", dialog);
		//			firstsend();
		//		}else{
		//			ToastUtil.show_short(context, "网络失败，请检查网络");
		//		}

	}
	private void setdate() {
		// TODO Auto-generated method stub
		ceshilist =new ArrayList<LanguageSearchInside>();
		ceshilist1=new LanguageSearchInside();
		ceshilist1.setTyPe("1");
		ceshilist1.setName("西游记");
		ceshilist1.setPath("http://192.168.1.113:8080/wt/asset/audio/cz_xyj_75.mp3");
		ceshilist.add(ceshilist1);

		ceshilist1=new LanguageSearchInside();
		ceshilist1.setTyPe("2");
		ceshilist1.setName("北京交通广播");
		ceshilist1.setPath("mms://alive.rbc.cn/fm1039");
		ceshilist.add(ceshilist1);

		ceshilist1=new LanguageSearchInside();
		ceshilist1.setTyPe("1");
		ceshilist1.setName("甜蜜蜜");
		ceshilist1.setPath("http://192.168.1.113:8080/wt/asset/audio/甜蜜蜜.mp3");
		ceshilist.add(ceshilist1);

		ceshilist1=new LanguageSearchInside();
		ceshilist1.setTyPe("2");
		ceshilist1.setName("北京音乐广播");  
		ceshilist1.setPath("mms://alive.rbc.cn/fm974");
		ceshilist.add(ceshilist1);
		ceshilist1=new LanguageSearchInside();
		ceshilist1.setTyPe("1");
		ceshilist1.setName("西游记");
		ceshilist1.setPath("http://192.168.1.113:8080/wt/asset/audio/cz_xyj_75.mp3");
		ceshilist.add(ceshilist1);

		ceshilist1=new LanguageSearchInside();
		ceshilist1.setTyPe("2");
		ceshilist1.setName("北京交通广播");
		ceshilist1.setPath("mms://alive.rbc.cn/fm1039");
		ceshilist.add(ceshilist1);

		ceshilist1=new LanguageSearchInside();
		ceshilist1.setTyPe("1");
		ceshilist1.setName("甜蜜蜜");
		ceshilist1.setPath("http://192.168.1.113:8080/wt/asset/audio/甜蜜蜜.mp3");
		ceshilist.add(ceshilist1);

		ceshilist1=new LanguageSearchInside();
		ceshilist1.setTyPe("2");
		ceshilist1.setName("北京音乐广播");  
		ceshilist1.setPath("mms://alive.rbc.cn/fm974");
		ceshilist.add(ceshilist1);

		adapter = new LanguageSearchAdapter(context, ceshilist);
		listView.setAdapter(adapter);
		setitemlistener();
	}
	private void setitemlistener() {
		// TODO Auto-generated method stub
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(ceshilist.get(position).getTyPe().equals("1")){
					Intent intent=new Intent(LanguageSearchActivity.this,AudioActivity.class);
					Bundle bundle=new Bundle();
					bundle.putString("path", ceshilist.get(position).getPath());
					bundle.putString("content", ceshilist.get(position).getName());
					intent.putExtras(bundle);
					startActivity(intent);
				}else{
					Intent intent=new Intent(LanguageSearchActivity.this,VideoActivity.class);
					Bundle bundle=new Bundle();
					bundle.putString("path", ceshilist.get(position).getPath());
					bundle.putString("content", ceshilist.get(position).getName());
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}
		});


		adapter.setOnListener(new OnListener() {

			@Override
			public void stop(int position) {
				// TODO Auto-generated method stub
				Toast.makeText(context,"播放暂停", 1).show();
			}
		});
	}
	private void firstsend() {
		// 第一次进入该界面获取数据
		// 发送数据
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
			private String ReturnType;
			private String SessionId;
			private String Message;
			@Override
			public void onResponse(JSONObject arg0) {
				if(dialog!=null){
					dialog.dismiss();
				}
				Log.e("第一次数据返回数据", arg0 + "");
				try {
					ReturnType=	arg0.getString("ReturnType");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					SessionId=	arg0.getString("SessionId");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Message=	arg0.getString("Message");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(ReturnType.equals("1001")){
					Toast.makeText(context,"绑定成功", 1).show();
				}else{
					Toast.makeText(context,"绑定失败，请稍后再试", 1).show();
				}
			}
		};
		ErrorListener errorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				if(dialog!=null){
					dialog.dismiss();
				}
				// TODO Auto-generated method stub
				Log.e("第一次数据返回异常", arg0 + "");
			}
		};
		JSONObject jsonObject = new JSONObject();
		RegisterRequest requests = new RegisterRequest();
		try {
			jsonObject.put("Machine", requests.machine);
			jsonObject.put("MobileType", requests.type);
			jsonObject.put("ScreenSize", requests.screen);
			jsonObject.put("IMEI", requests.imei);
			jsonObject.put("SessionId",Utils.getSessionId(this));
			JsonObjectRequest request = new JsonObjectRequest(
					Request.Method.POST, GlobalConfig.logoutUrl,jsonObject,
					listener, errorListener);
			requestQueue.add(request);
			requestQueue.start();
			Log.i("获取第一次数据路径及信息", GlobalConfig.logoutUrl + jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setlistener() {
		// TODO Auto-generated method stub
		lin_findvoice.setOnClickListener(this);
		lin_left.setOnClickListener(this);
		lin_right.setOnClickListener(this);
		imageView_stop.setOnClickListener(this);
		lin_more.setOnClickListener(this);

		lin_findvoice.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					circle_images.start();
					tv_voice.setText("");// 清空显示内容
					mIatResults.clear();
					// 设置参数
					setParam();
					//			3.开始听写  
					mIat.startListening( mRecoListener);  
					image.setImageResource(R.drawable.barcode_take_picture_pressed);
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					circle_images.stop();
					//3.停止听写  
					mIat.stopListening();
					image.setImageResource(R.drawable.image_icon_search);
				}
				return true;
			}
		});
	}
	private void setview() {
		// TODO Auto-generated method stub
		tv_voice = (EditText) findViewById(R.id.tv_voice);//语音搜索内容展示
		tv_voice.setFocusable(false);
		lin_findvoice = (LinearLayout) findViewById(R.id.lin_findvoice);//语音搜索
		lin_left = (LinearLayout) findViewById(R.id.lin_left);//上一首
		lin_right = (LinearLayout) findViewById(R.id.lin_right);//下一首
		imageView_stop = (ImageView) findViewById(R.id.imageView_stop);//暂停
		lin_more = (LinearLayout) findViewById(R.id.lin_more);//电台列表
		listView = (ListView) findViewById(R.id.listView);//数据展示

		image= (ImageView)findViewById(R.id.imageView);//话筒按钮
		circle_images= (CircleWaveView)findViewById(R.id.Circle_imageView);//波形图
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_findvoice:

			break;
		case R.id.lin_left:

			break;
		case R.id.lin_right:

			break;
		case R.id.imageView_stop:

			break;
		case R.id.lin_more:
			startActivity(new Intent(LanguageSearchActivity.this, HomeActivity.class));
			break;
		default:
			break;
		}
	}


	/////
	/**
	 * 参数设置
	 * 
	 * @param param
	 * @return
	 */
	public void setParam() {
		// 清空参数
		mIat.setParameter(SpeechConstant.PARAMS, null);
		//搜索引擎  云搜索
		mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
		// 设置返回结果格式
		mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
		// 设置语言
		mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		// 设置语言区域
		mIat.setParameter(SpeechConstant.ACCENT, "mandarin");

		mIat.setParameter(SpeechConstant.DOMAIN, "iat");

		// 设置听写结果是否结果动态修正，为“1”则在听写过程中动态递增地返回结果，否则只在听写结束之后返回最终结果
		// 注：该参数暂时只对在线听写有效
		mIat.setParameter(SpeechConstant.ASR_DWA,  "1");
		// 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
		mIat.setParameter(SpeechConstant.VAD_BOS, "10000");

		// 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
		mIat.setParameter(SpeechConstant.VAD_EOS, "5000");

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 退出时释放连接
		mIat.cancel();
		mIat.destroy();
	}

	private void printResult(RecognizerResult results) {
		String text = JsonParser.parseIatResult(results.getResultString());

		String sn = null;
		// 读取json结果中的sn字段
		try {
			JSONObject resultJson = new JSONObject(results.getResultString());
			sn = resultJson.optString("sn");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		mIatResults.put(sn, text);

		StringBuffer resultBuffer = new StringBuffer();
		for (String key : mIatResults.keySet()) {
			resultBuffer.append(mIatResults.get(key));
		}

		tv_voice.setText(resultBuffer.toString());
		tv_voice.setSelection(tv_voice.length());
	}


	//听写监听器
	private RecognizerListener mRecoListener = new RecognizerListener(){
		//听写结果回调接口(返回Json格式结果，用户可参见附录12.1)；
		//一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
		//关于解析Json的代码可参见MscDemo中JsonParser类；
		//isLast等于true时会话结束。
		public void onResult(RecognizerResult results, boolean isLast) {
			printResult(results);

			Log.e("Result:",results.getResultString ());}
		//会话发生错误回调接口
		public void onError(SpeechError error) {
			error.getPlainDescription(true); //获取错误码描述
		}
		//开始录音
		public void onBeginOfSpeech() {
			// 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
			Toast.makeText(context, "开始说话", 1).show();
		}
		//音量值0~30
		public void onVolumeChanged(int volume){}
		//结束录音
		public void onEndOfSpeech() {
			// 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
			circle_images.stop();
			image.setImageResource(R.drawable.image_icon_search);
			Toast.makeText(context, "结束说话", 1).show();
		}
		//扩展用接口
		public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {

			// 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
			// 若使用本地能力，会话id为null
			//	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
			//		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
			//		Log.d(TAG, "session id =" + sid);
			//	}
		}
		@Override
		public void onVolumeChanged(int arg0, byte[] arg1) {
			// TODO Auto-generated method stub
			//			Toast.makeText(TestDemo.this, "当前正在说话，音量大小：" + arg0+"", 1).show();
			//			Log.d(TAG, "返回音频数据："+arg1.length);
		}
	}; 
	
	
	long waitTime = 2000;
	long touchTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - touchTime) >= waitTime) {
				ToastUtil.show_short(context, "再按一次退出");
				touchTime = currentTime;
			} else {
//				android.os.Process.killProcess(android.os.Process.myPid());
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
