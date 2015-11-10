package com.wotingfm.activity.person.playhistory.activity;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenstec.activity.BaseActivity;
import com.wotingfm.R;
import com.wotingfm.activity.person.playhistory.adapter.PlayHistoryAdapter;
import com.wotingfm.activity.person.playhistory.model.PlayHistory;
import com.wotingfm.activity.person.playhistory.model.PlayHistoryInside;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.PhoneMessage;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;

/*
 * 播放历史
 */
public class PlayHistoryActivity extends BaseActivity implements OnClickListener{
	private ListView mListView;
	private Dialog dialog;
	protected List<PlayHistory> Responselist;
	private LinearLayout lin_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playhistory);
		setview();//设置界面
		setlistener();//设置监听
		send();
	}
	
	private void setview() {
		// TODO Auto-generated method stub
		mListView=(ListView)findViewById(R.id.listview_history);
		lin_back=(LinearLayout)findViewById(R.id.head_left_btn);
	}

	private void setlistener() {
		// TODO Auto-generated method stub
		lin_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.head_left_btn:
			finish();
			break;
		default:
			break;
		}
	}
	private void send() {
		// TODO Auto-generated method stub
		RequestQueue requestQueue = Volley
				.newRequestQueue(PlayHistoryActivity.this);
		Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
			private String SessionId;
			private String ReturnType;
			private String GroupName;
			private String Message;
			private List<PlayHistoryInside> list;
			private String PlayHistory;
			@Override
			//此处肯定需要返回一个节目信息的list			
			public void onResponse(JSONObject arg0) {
				if (dialog != null) {
					dialog.dismiss();
				}
				try {
					  //获取列表
					PlayHistory = arg0.getString("PlayHistory");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				    
				 Gson gson=new Gson(); 
				 //用PlayHistoryInside的格式去除数据并放到对应其类型的一个list当中
				 list = gson.fromJson(PlayHistory, new TypeToken<List<PlayHistoryInside>>(){}.getType());
				           
				try {
					SessionId = arg0.getString("SessionId");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ReturnType = arg0.getString("ReturnType");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Message = arg0.getString("Message");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (ReturnType != null && ReturnType.equals("1001")) {
					ToastUtil.show_short(PlayHistoryActivity.this, "获取成功，精彩马上呈现");
					
					if (list == null || list.size() == 0) {
						ToastUtil.show_short(PlayHistoryActivity.this, "您当前没有历史记录");
					} else {
						PlayHistoryAdapter adapter = new PlayHistoryAdapter(PlayHistoryActivity.this, list);
						mListView.setAdapter(adapter);
					}
				}
				if (ReturnType != null && ReturnType.equals("1002")) {
					ToastUtil.show_short(PlayHistoryActivity.this, "创建失败，失败原因"+Message);
				} else {
					if (Message != null && !Message.trim().equals("")) {
						ToastUtil.show_short(PlayHistoryActivity.this,
								Message + "获取历史消息，请稍后重试");
					}
				}
			
			}
		};
		ErrorListener errorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
			}
		};
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("SessionId", Utils.getSessionId(PlayHistoryActivity.this));
			jsonObject.put("UserId", Utils.getUserId(PlayHistoryActivity.this));
			jsonObject.put("Machine",PhoneMessage.productor);
			jsonObject.put("MobileType", PhoneMessage.model);
			jsonObject.put("ScreenSize", PhoneMessage.ScreenWidth+"x"+PhoneMessage.ScreenHeight);
			jsonObject.put("IMEI", PhoneMessage.imei);
			jsonObject.put("GPS",PhoneMessage.getGps(PlayHistoryActivity.this));
			JsonObjectRequest request = new JsonObjectRequest(
					Request.Method.POST, GlobalConfig.playHistoryUrl,
					jsonObject, listener, errorListener);
			requestQueue.add(request);		
			requestQueue.start();
			dialog = Utils.Dialogph(PlayHistoryActivity.this, "正在获取数据", dialog);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
