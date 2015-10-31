package com.wotingfm.activity.interphone.grouplist.activity;


import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.wotingfm.R;
import com.wotingfm.activity.interphone.SpeakAddGroupActivity;
import com.wotingfm.activity.interphone.grouplist.adapter.TalkGroupListAdapter;
import com.wotingfm.activity.interphone.grouplist.model.TalkGroupList;
import com.wotingfm.activity.interphone.grouplist.model.TalkGroupListInside;
import com.wotingfm.activity.interphone.grouplist.response.TalkGroupListResponse;
import com.wotingfm.activity.interphone.grouplist.service.TalkGroupListService;
import com.wotingfm.activity.interphone.grouptalk.activity.GroupTalkActivity;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;
/*
 * 对讲机-获取通讯录联系人
 */
public class TalkGroupListActivity extends BaseActivity  {
	private ListView listView;
	private Dialog dialog;
	private ArrayList<TalkGroupList> ceshilist;
	private TalkGroupList ceshilist1;
	private TalkGroupListAdapter adapter;
	private TalkGroupListActivity context;
	private LinearLayout head_left_btn;
	private LinearLayout head_right_btn;
	private TalkGroupList alllist;
	private List<TalkGroupListInside> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_talk_grouplist);
		context=this;
		listView=(ListView)findViewById(R.id.listView);

		head_left_btn=(LinearLayout)findViewById(R.id.head_left_btn);
		head_left_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		head_right_btn=(LinearLayout)findViewById(R.id.head_right_btn);
		head_right_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "加入某个小组", 0).show();
				Intent intent=new Intent(context,SpeakAddGroupActivity.class);
				startActivity(intent);
			}
		});

		//		setdate();
		//		setadapter();
		send();
	}

	//	private void setadapter() {
	//		// TODO Auto-generated method stub
	//		adapter=new TalkGroupListAdapter(context, ceshilist);
	//		listView.setAdapter(adapter);
	//		setlistener();
	//	}
	//	private void setdate() {
	//		// TODO Auto-generated method stub
	//		ceshilist =new ArrayList<TalkGroupList>();
	//		ceshilist1=new TalkGroupList();
	//		ceshilist1.setNumber("15");
	//		ceshilist1.setName("技术组");
	//		ceshilist.add(ceshilist1);
	//
	//		ceshilist1=new TalkGroupList();
	//		ceshilist1.setNumber("20");
	//		ceshilist1.setName("开发组");
	//		ceshilist.add(ceshilist1);
	//
	//		ceshilist1=new TalkGroupList();
	//		ceshilist1.setNumber("25");
	//		ceshilist1.setName("销售组");
	//		ceshilist.add(ceshilist1);
	//
	//		ceshilist1=new TalkGroupList();
	//		ceshilist1.setNumber("15");
	//		ceshilist1.setName("管理组");
	//		ceshilist.add(ceshilist1);
	//
	//		ceshilist1=new TalkGroupList();
	//		ceshilist1.setNumber("25");
	//		ceshilist1.setName("后勤组");
	//		ceshilist.add(ceshilist1);
	//	}
	public void send(){
		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			dialog = Utils.Dialogph(context, "正在获取数据", dialog);
			int randomX = (int) System.currentTimeMillis();
			new TalkGroupListService(context, this, TaskConstant.Task_TalkGroupList).sendRequest(randomX);
		}else{
			ToastUtil.show_short(context, "网络失败，请检查网络");
		}
	}

	public void refreshUI(Message msg) {
		switch (msg.what) {
		case TaskConstant.Task_TalkGroupList:
			if(dialog!=null){
				dialog.dismiss();
			}
			if(msg.obj!=null && msg.obj instanceof TalkGroupListResponse){
				TalkGroupListResponse response=(TalkGroupListResponse) msg.obj;
				try {
					alllist = response.list;
				} catch (Exception e) {
					e.printStackTrace();
				}
				alllist.getReturnType();
				alllist.getSessionId();
				list=alllist.getGroupList();
				if(list==null||list.size()==0){
					ToastUtil.show_short(context, "您当前没有数据");
				}else{
					adapter=new TalkGroupListAdapter(context, list);
					listView.setAdapter(adapter);
					setlistener();
				}
			}else{
				ToastUtil.show_short(context, "无法获取数据");
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
				Toast.makeText(context, "开始小组对讲", 0).show();
				Intent intent=new Intent(context,GroupTalkActivity.class);
				startActivity(intent);

			}
		});
	}

}
