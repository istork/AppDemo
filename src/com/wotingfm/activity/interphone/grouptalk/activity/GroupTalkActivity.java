package com.wotingfm.activity.interphone.grouptalk.activity;


import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wotingfm.R;
import com.wotingfm.activity.interphone.grouptalk.adapter.GroupTalkAdapter;
import com.wotingfm.activity.interphone.grouptalk.model.GroupTalk;
import com.wotingfm.activity.interphone.grouptalk.model.GroupTalkInside;
import com.wotingfm.activity.interphone.grouptalk.response.GroupTalkResponse;
import com.wotingfm.activity.interphone.grouptalk.service.GroupTalkService;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.BaseActivity;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;
/*
 * 对讲机-群组对讲
 * 辛龙
 */
public class GroupTalkActivity extends BaseActivity  {
	private GridView gridView;
	private List<GroupTalkInside> list;
	private Dialog dialog;
	private ArrayList<GroupTalk> ceshilist;
	private GroupTalk ceshilist1;
	private GroupTalkAdapter adapter;
	private GroupTalkActivity context;
	private LinearLayout head_left_btn;
	private ImageView image_talk;
	private GroupTalk alllist;
	private String ReturnType;
	private String SessionId;
	private String GroupId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_talk_group);
		context=this;
		gridView=(GridView)findViewById(R.id.gridView);
		head_left_btn=(LinearLayout)findViewById(R.id.head_left_btn);
		head_left_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		image_talk = (ImageView) findViewById(R.id.image_talk);
		image_talk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtil.show_long(GroupTalkActivity.this, "开始对讲连接。。。");
			}
		});
		//    	setdate();
		//    	setadapter();
		send();
	}

	//	private void setadapter() {
	//		// TODO Auto-generated method stub
	//		adapter=new GroupTalkAdapter(context, ceshilist);
	//		gridView.setAdapter(adapter);
	//		setlistener();
	//	}
	//	private void setdate() {
	//	// TODO Auto-generated method stub
	//	ceshilist =new ArrayList<GroupTalk>();
	//	ceshilist1=new GroupTalk();
	//	ceshilist1.setName("小辛");
	//	ceshilist.add(ceshilist1);
	//	
	//	ceshilist1=new GroupTalk();
	//	ceshilist1.setName("小赵");
	//	ceshilist.add(ceshilist1);
	//	
	//	ceshilist1=new GroupTalk();
	//	ceshilist1.setName("小王");
	//	ceshilist.add(ceshilist1);
	//	
	//	ceshilist1=new GroupTalk();
	//	ceshilist1.setName("小李");
	//	ceshilist.add(ceshilist1);
	//	
	//	ceshilist1.setNumber("25");
	//	ceshilist1.setName("小钱");
	//	ceshilist.add(ceshilist1);
	//}
	public void send(){
		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			dialog = Utils.Dialogph(context, "正在获取数据", dialog);
			int randomX = (int) System.currentTimeMillis();
			new GroupTalkService(context, this, TaskConstant.Task_GroupTalk).sendRequest(randomX);
		}else{
			ToastUtil.show_short(context, "网络失败，请检查网络");
		}
	}

	public void refreshUI(Message msg) {
		switch (msg.what) {
		case TaskConstant.Task_GroupTalk:
			if(dialog!=null){
				dialog.dismiss();
			}
			if(msg.obj!=null && msg.obj instanceof GroupTalkResponse){
				GroupTalkResponse response=(GroupTalkResponse) msg.obj;
				try {
					alllist = response.list;
				} catch (Exception e) {
					e.printStackTrace();
				}
				ReturnType=	alllist.getReturnType();
				SessionId=alllist.getSessionId();
				GroupId=alllist.getGroupId();

				list=alllist.getUserList();

				if(list==null||list.size()==0){
					ToastUtil.show_short(context, "您当前没有数据");
				}else{
					adapter=new GroupTalkAdapter(context, list);
					gridView.setAdapter(adapter);
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
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});
	}

}
