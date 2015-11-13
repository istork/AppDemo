package com.wotingfm.activity.home.citylist.activity;


import java.util.ArrayList;

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
import com.wotingfm.R;
import com.wotingfm.activity.home.citylist.adapter.CityGridAdapter;
import com.wotingfm.activity.home.citylist.adapter.CityListAdapter;
import com.wotingfm.activity.home.citylist.model.CityListInside;
import com.wotingfm.activity.home.citylist.model.RadioListInside;
import com.wotingfm.activity.login.register.request.RegisterRequest;
import com.wotingfm.activity.login.splash.activity.SplashActivity;
import com.wotingfm.activity.login.welcome.activity.WelcomeActivity;
import com.wotingfm.activity.main.MainActivity;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
/*
 * 选择省市台
 * 辛龙
 */
public class CityListActivity extends Activity implements OnClickListener{

	private LinearLayout head_left_btn;
	private LinearLayout lin_shoose;
	private ListView listView;
	private GridView gridView;
	private LinearLayout lin_grid;
	private CityListActivity context;
	private Dialog dialog;
	private SharedPreferences sharedPreferences;
	private String cityid;
	private ArrayList<RadioListInside> ceshilist_radio;
	private RadioListInside ceshilist1;
	private ArrayList<CityListInside> ceshilist_city;
	private CityListInside ceshilist2;
	private CityListAdapter cityadapter;
	private CityGridAdapter radioadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_citylist);
		context=this;
		
		sharedPreferences = this.getSharedPreferences("wotingfm",Context.MODE_PRIVATE);
		cityid = sharedPreferences.getString(StringConstant.CITYID, "cityid");//用户名，昵称
		
		setview();//设置界面
		setlistener();//设置监听
		
		/*
		 * 测试数据
		 */
		setcitydate();
		setradiodate();
		setadapter();
		
		
		
//		SharedPreferences sp=getSharedPreferences("wotingfm", Context.MODE_PRIVATE);
//		Editor et=sp.edit();
//		et.putString(StringConstant.CITYID, SessionId); 
//		et.commit();
		
		
//		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
//			dialog = Utils.Dialogph(context, "绑定中，请稍等", dialog);
//			send();
//		}else{
//			ToastUtil.show_short(context, "网络失败，请检查网络");
//		}
		
		
		
		
	}
	private void setadapter() {
			// TODO Auto-generated method stub
			cityadapter=new CityListAdapter(context, ceshilist_radio);
			listView.setAdapter(cityadapter);
			radioadapter=new CityGridAdapter(context, ceshilist_city);
			gridView.setAdapter(radioadapter);
//			setitemlistener();
		}
		private void setradiodate() {
			// TODO Auto-generated method stub
			ceshilist_radio =new ArrayList<RadioListInside>();
			ceshilist1=new RadioListInside();
			ceshilist1.setRadioName("电台a");
			ceshilist1.setRadioNews("这是对于电台a的简介");
			ceshilist1.setRadioPath("mms://alive.rbc.cn/fm1039");
			ceshilist_radio.add(ceshilist1);
			
			ceshilist1=new RadioListInside();
			ceshilist1.setRadioName("电台b");
			ceshilist1.setRadioNews("这是对于电台b的简介");
			ceshilist1.setRadioPath("mms://alive.rbc.cn/fm1039");
			ceshilist_radio.add(ceshilist1);
			
			ceshilist1=new RadioListInside();
			ceshilist1.setRadioName("电台c");
			ceshilist1.setRadioNews("这是对于电台c的简介");
			ceshilist1.setRadioPath("mms://alive.rbc.cn/fm1039");
			ceshilist_radio.add(ceshilist1);
			
			ceshilist1=new RadioListInside();
			ceshilist1.setRadioName("电台d");
			ceshilist1.setRadioNews("这是对于电台d的简介");
			ceshilist1.setRadioPath("mms://alive.rbc.cn/fm1039");
			ceshilist_radio.add(ceshilist1);
			
			ceshilist1=new RadioListInside();
			ceshilist1.setRadioName("电台e");
			ceshilist1.setRadioNews("这是对于电台e的简介");
			ceshilist1.setRadioPath("mms://alive.rbc.cn/fm1039");
			ceshilist_radio.add(ceshilist1);
			
			ceshilist1=new RadioListInside();
			ceshilist1.setRadioName("电台f");
			ceshilist1.setRadioNews("这是对于电台f的简介");
			ceshilist1.setRadioPath("mms://alive.rbc.cn/fm1039");
			ceshilist_radio.add(ceshilist1);
			
			ceshilist1=new RadioListInside();
			ceshilist1.setRadioName("电台g");
			ceshilist1.setRadioNews("这是对于电台g的简介");
			ceshilist1.setRadioPath("mms://alive.rbc.cn/fm1039");
			ceshilist_radio.add(ceshilist1);
			
			ceshilist1=new RadioListInside();
			ceshilist1.setRadioName("电台h");
			ceshilist1.setRadioNews("这是对于电台h的简介");
			ceshilist1.setRadioPath("mms://alive.rbc.cn/fm1039");
			ceshilist_radio.add(ceshilist1);
	
			
		}
		private void setcitydate() {
			// TODO Auto-generated method stub
			ceshilist_city =new ArrayList<CityListInside>();
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("北京");
			ceshilist2.setTyPe("1");
			ceshilist_city.add(ceshilist2);
			
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("天津");
			ceshilist2.setTyPe("2");
			ceshilist_city.add(ceshilist2);
			
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("上海");
			ceshilist2.setTyPe("2");
			ceshilist_city.add(ceshilist2);
			
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("重庆");
			ceshilist2.setTyPe("2");
			ceshilist_city.add(ceshilist2);
			
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("河北");
			ceshilist2.setTyPe("2");
			ceshilist_city.add(ceshilist2);
			
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("山西");
			ceshilist2.setTyPe("2");
			ceshilist_city.add(ceshilist2);
			
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("辽宁");
			ceshilist2.setTyPe("2");
			ceshilist_city.add(ceshilist2);
			
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("吉林");
			ceshilist2.setTyPe("2");
			ceshilist_city.add(ceshilist2);
			
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("黑龙江");
			ceshilist2.setTyPe("2");
			ceshilist_city.add(ceshilist2);
			
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("江苏");
			ceshilist2.setTyPe("2");
			ceshilist_city.add(ceshilist2);
			
			ceshilist2=new CityListInside();
			ceshilist2.setCityName("浙江");
			ceshilist2.setTyPe("2");
			ceshilist_city.add(ceshilist2);
			
			
		}
	private void send() {
		// TODO Auto-generated method stub
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		Listener<JSONObject> listener = new Response.Listener<JSONObject>() {

			private String ReturnType;
			private String SessionId;
			private String UserInfos;

			@Override
			public void onResponse(JSONObject arg0) {
				if(dialog!=null){
					dialog.dismiss();
				}
				Log.e("启动返回数据", arg0 + "");
				try {
					ReturnType=	arg0.getString("ReturnType");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			}
		};
		ErrorListener errorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				if(dialog!=null){
					dialog.dismiss();
				}
				Log.e("启动异常", arg0 + "");
			}
		};
		JSONObject jsonObject = new JSONObject();
		RegisterRequest requests = new RegisterRequest();
		try {
			jsonObject.put("Machine", requests.machine);
			jsonObject.put("MobileType", requests.type);
			jsonObject.put("ScreenSize", requests.screen);
			jsonObject.put("IMEI", requests.imei);
			JsonObjectRequest request = new JsonObjectRequest(
					Request.Method.POST, GlobalConfig.splashUrl,jsonObject,
					listener, errorListener);
			requestQueue.add(request);
			requestQueue.start();
			Log.i("启动路径及信息", GlobalConfig.splashUrl + jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setlistener() {
		// TODO Auto-generated method stub
		head_left_btn.setOnClickListener(this);
		lin_shoose.setOnClickListener(this);
	}

	private void setview() {
		// TODO Auto-generated method stub
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		lin_shoose = (LinearLayout) findViewById(R.id.lin_shoose);//切换城市
		lin_grid = (LinearLayout) findViewById(R.id.lin_grid);//展示城市分类
		listView = (ListView) findViewById(R.id.listView);
		gridView = (GridView) findViewById(R.id.gridView);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.head_left_btn:
		finish();
		break;
	case R.id.lin_shoose:
		if(lin_grid.getVisibility()==View.VISIBLE){
			lin_grid.setVisibility(View.GONE);
		}else{
			lin_grid.setVisibility(View.VISIBLE);
		}
		break;
	default:
		break;
	}
	}



}
