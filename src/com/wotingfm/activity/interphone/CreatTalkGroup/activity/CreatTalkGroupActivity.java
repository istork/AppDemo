package com.wotingfm.activity.interphone.CreatTalkGroup.activity;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wotingfm.R;
import com.wotingfm.activity.interphone.CreatTalkGroup.adapter.CreatTalkGroupAdapter;
import com.wotingfm.activity.interphone.CreatTalkGroup.adapter.CreatTalkGroupNoAdapter;
import com.wotingfm.activity.interphone.CreatTalkGroup.model.CreatTalkGroup;
import com.wotingfm.activity.interphone.CreatTalkGroup.model.CreatTalkGroupInside;
import com.wotingfm.activity.interphone.CreatTalkGroup.response.CreatTalkGroupResponse;
import com.wotingfm.activity.interphone.CreatTalkGroup.service.CreatTalkGroupService;
import com.wotingfm.activity.interphone.grouplist.activity.TalkGroupListActivity;
import com.wotingfm.activity.interphone.talkperson.fragment.TalkPersonFragment;
import com.wotingfm.activity.interphone.talkperson.model.TalkPerson;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;
/*
 * 对讲机-创建小组
 */
public class CreatTalkGroupActivity extends BaseActivity   implements OnClickListener{
	private View headview;
	private ListView listView;
	private Dialog dialog;
	private CreatTalkGroupAdapter adapter;
	private CreatTalkGroupNoAdapter noadapter;
	private CreatTalkGroupActivity context;
	private LinearLayout lin_check;
	private TalkPerson ceshilist1;
//	private ArrayList<TalkPerson> ceshilistall;
	private LinearLayout head_left_btn;
	private LinearLayout head_right_btn;
	private CreatTalkGroup all_list;
	private List<CreatTalkGroupInside>   list;
	private String SessionId;
	private String ReturnType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupadd);
		context = this;
		listView=(ListView)findViewById(R.id.listView);
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		head_left_btn.setOnClickListener(this);
		head_right_btn = (LinearLayout) findViewById(R.id.head_right_btn);
		head_right_btn.setOnClickListener(this);
		//头部view
		headview=LayoutInflater.from(context).inflate(R.layout.head_creattalkgroup, null);
		lin_check = (LinearLayout) headview.findViewById(R.id.lin_check);
		lin_check.setOnClickListener(this);
		//添加头部view
		listView.addHeaderView(headview);
		/*
		 * 测试数据以及代码
		 */
//				if(TalkPersonFragment.ceshilist==null||TalkPersonFragment.ceshilist.size()==0){
//					ToastUtil.show_short(context, "无数据");
//					setdate();
//					setadapter();
//				}else{
//					ToastUtil.show_short(context, "存在数据");
//					ceshilistall=TalkPersonFragment.ceshilist;
//					adapter=new CreatTalkGroupAdapter(context, ceshilistall);
//					listView.setAdapter(adapter);
//					setlistener();
//				}


		send();
	}
//	private void setadapter() {
//		// TODO Auto-generated method stub
//		adapter=new CreatTalkGroupAdapter(context, ceshilistall);
//		listView.setAdapter(adapter);
//		setlistener();
//	}
//	private void setdate() {
//		// TODO Auto-generated method stub
//		ceshilistall =new ArrayList<TalkPerson>();
//		ceshilist1=new TalkPerson();
//		ceshilist1.setID("1");
//		ceshilist1.setName("小辛A");
//		ceshilistall.add(ceshilist1);
//		ceshilist1=new TalkPerson();
//		ceshilist1.setID("2");
//		ceshilist1.setName("小辛B");
//		ceshilistall.add(ceshilist1);
//		ceshilist1=new TalkPerson();
//		ceshilist1.setID("3");
//		ceshilist1.setName("小辛C");
//		ceshilistall.add(ceshilist1);
//		ceshilist1=new TalkPerson();
//		ceshilist1.setID("4");
//		ceshilist1.setName("小辛D");
//		ceshilistall.add(ceshilist1);
//		ceshilist1=new TalkPerson();
//		ceshilist1.setID("5");
//		ceshilist1.setName("小辛E");
//		ceshilistall.add(ceshilist1);
//	}
	public void send(){
		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			dialog = Utils.Dialogph(context, "正在获取数据", dialog);
			String id = Utils.getSessionId(context);
			String userid = Utils.getUserId(context);
			int randomX = (int) System.currentTimeMillis();
			new CreatTalkGroupService(context, this, TaskConstant.Task_CreatTalkGroup).sendRequest(randomX,id,userid);
		}else{
			ToastUtil.show_short(context, "网络失败，请检查网络");
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.head_left_btn:
			finish();
			break;
		case R.id.head_right_btn:
			if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
				dialog = Utils.Dialogph(context, "正在创建小组", dialog);
				senddata();
			}else{
				ToastUtil.show_short(context, "网络失败，请检查网络");
			}

			break;
		default:
			break;
		}
	}

	private void senddata() {
		// TODO Auto-generated method stub
		RequestQueue requestQueue = Volley.newRequestQueue(CreatTalkGroupActivity.this);
		Listener<JSONObject> listener=new Response.Listener<JSONObject>() {

			private String SessionId;
			private String ReturnType;
			private String GroupName;
			private String Message;

			@Override
			public void onResponse(JSONObject arg0) {
				if(dialog!=null){
					dialog.dismiss();
				}
				Log.i("创建小组返回值", arg0+"");
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
					GroupName = arg0.getString("GroupName");
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
				if(ReturnType!=null&&ReturnType.equals("1001")){
					ToastUtil.show_short(CreatTalkGroupActivity.this, "创建成功");
				}else{ 
					if(Message!=null&&!Message.trim().equals("")){
						ToastUtil.show_short(CreatTalkGroupActivity.this, Message+"");
					}else{
						ToastUtil.show_short(CreatTalkGroupActivity.this, "创建失败，请稍后再试");
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
			jsonObject.put("SessionId",Utils.getSessionId(context));
			jsonObject.put("Creator",Utils.getUserId(context));
			jsonObject.put("Menbers","021,023,024");
			jsonObject.put("GroupName","小辛测试");
			JsonObjectRequest request = new JsonObjectRequest(
					Request.Method.POST, GlobalConfig.talkgroupcreatUrl, jsonObject,
					listener, errorListener);
			requestQueue.add(request); 
			requestQueue.start();
			Log.i("创建对讲小组路径", GlobalConfig.talkgroupcreatUrl+"");
			Log.i("创建对讲小组提交数据", jsonObject+"");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public void refreshUI(Message msg) {
	switch (msg.what) {
	case TaskConstant.Task_CreatTalkGroup:
		if(dialog!=null){
			dialog.dismiss();
		}
		if(msg.obj!=null && msg.obj instanceof CreatTalkGroupResponse){
			CreatTalkGroupResponse response=(CreatTalkGroupResponse) msg.obj;
			try {
				all_list = response.list;
			} catch (Exception e) {
				e.printStackTrace();
			}
				SessionId = all_list.getSessionId();
				ReturnType = all_list.getReturnType();
			if(ReturnType!=null&&ReturnType.equals("1001")){
				   list=all_list.getUserList();
					if(list==null||list.size()==0){
						ToastUtil.show_short(context, "您当前没有数据");
						noadapter=new CreatTalkGroupNoAdapter(context);
						listView.setAdapter(noadapter);
					}else{
						adapter=new CreatTalkGroupAdapter(context, list);
						listView.setAdapter(adapter);
						setlistener();
					}
			}
         
		}else{
			ToastUtil.show_short(context, "无法获取数据");
			noadapter=new CreatTalkGroupNoAdapter(context);
			listView.setAdapter(noadapter);
		}
		break;

	}
}
private void setlistener() {
	// TODO Auto-generated method stub
	listView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			if(list.get(position-1).getCheck().equals("1")){
				list.get(position-1).setCheck("2");
				Toast.makeText(context, "选中"+list.get(position-1).getUserName(), 0).show();
			}else{
				list.get(position-1).setCheck("1");
				Toast.makeText(context,"取消"+list.get(position-1).getUserName(), 1).show();
			}
			adapter.notifyDataSetChanged();

		}
	});
}

}
