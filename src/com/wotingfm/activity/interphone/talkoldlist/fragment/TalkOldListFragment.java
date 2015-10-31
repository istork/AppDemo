package com.wotingfm.activity.interphone.talkoldlist.fragment;


import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.wotingfm.R;
import com.wotingfm.activity.interphone.SpeakAddActivity;
import com.wotingfm.activity.interphone.grouplist.activity.TalkGroupListActivity;
import com.wotingfm.activity.interphone.grouptalk.activity.GroupTalkActivity;
import com.wotingfm.activity.interphone.persontalk.activity.PersonTalkActivity;
import com.wotingfm.activity.interphone.talkoldlist.adapter.TalkOldListAdapter;
import com.wotingfm.activity.interphone.talkoldlist.model.TalkOldList;
import com.wotingfm.activity.interphone.talkoldlist.model.TalkOldListInside;
import com.wotingfm.activity.interphone.talkoldlist.response.TalkOldListResponse;
import com.wotingfm.activity.interphone.talkoldlist.service.TalkOldListService;
import com.wotingfm.activity.interphone.talkperson.adapter.TalkPersonAdapter;
import com.wotingfm.activity.interphone.talkperson.adapter.TalkPersonNoAdapter;
import com.wotingfm.activity.interphone.talkperson.model.TalkPerson;
import com.wotingfm.activity.interphone.talkperson.response.TalkPersonResponse;
import com.wotingfm.activity.interphone.talkperson.service.TalkPersonService;
import com.wotingfm.activity.interphone.talkpersonnews.activity.TalkPersonNewsActivity;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.BaseFragment;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;
/*
 * 对讲机-获取联系列表，包括群组跟个人
 */
public class TalkOldListFragment extends BaseFragment  {
	private FragmentActivity context;
	private ListView listView;
	private Dialog dialog;
//	private ArrayList<TalkOldList> ceshilist;
//	private TalkOldList ceshilist1;
	private TalkOldListAdapter adapter;
	private TalkOldList alllist;
	private List<TalkOldListInside> list;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_talkoldlist, container, false);
    	context = this.getActivity();
    	listView=(ListView)rootView.findViewById(R.id.listView);
    	
    	
//    	setdate();
//    	setadapter();
    	send();
    	return rootView;
    }
	
	
//	private void setadapter() {
//		// TODO Auto-generated method stub
//		adapter=new TalkOldListAdapter(context, ceshilist);
//		listView.setAdapter(adapter);
//		setlistener();
//	}
//	private void setdate() {
//	// TODO Auto-generated method stub
//	ceshilist =new ArrayList<TalkOldList>();
//	ceshilist1=new TalkOldList();
//	ceshilist1.setType("1");
//	ceshilist1.setName("小辛");
//	ceshilist.add(ceshilist1);
//	ceshilist1=new TalkOldList();
//	ceshilist1.setType("2");
//	ceshilist1.setName("技术小组");
//	ceshilist.add(ceshilist1);
//	ceshilist1=new TalkOldList();
//	ceshilist1.setType("2");
//	ceshilist1.setName("销售小组");
//	ceshilist.add(ceshilist1);
//	ceshilist1=new TalkOldList();
//	ceshilist1.setType("1");
//	ceshilist1.setName("小王");
//	ceshilist.add(ceshilist1);
//	ceshilist1=new TalkOldList();
//	ceshilist1.setType("2");
//	ceshilist1.setName("管理小组");
//	ceshilist.add(ceshilist1);
//}
	public void send(){
		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			dialog = Utils.Dialogph(context, "正在获取数据", dialog);
			int randomX = (int) System.currentTimeMillis();
			new TalkOldListService(context, this, TaskConstant.Task_TalkOldList).sendRequest(randomX);
		}else{
			ToastUtil.show_short(context, "网络失败，请检查网络");
		}
	}
    
	public void refreshUI(Message msg) {
		switch (msg.what) {
		case TaskConstant.Task_TalkOldList:
			if(dialog!=null){
				dialog.dismiss();
			}
			if(msg.obj!=null && msg.obj instanceof TalkOldListResponse){
				TalkOldListResponse response=(TalkOldListResponse) msg.obj;
				try {
					alllist = response.list;
				} catch (Exception e) {
					e.printStackTrace();
				}

				list=alllist.getHistoryList();
				if(list==null||list.size()==0){
					ToastUtil.show_short(context, "您当前没有数据");
				}else{
					adapter=new TalkOldListAdapter(context, list);
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
			if(	list.get(position).getObjType()!=null&&
					list.get(position).getObjType().equals("User")){
				Toast.makeText(context, "开始个人对讲", 0).show();
				Intent intent=new Intent(context,PersonTalkActivity.class);
				Bundle bundel=new Bundle();
				bundel.putString("type", "talkoldlist");
				bundel.putSerializable("data", list.get(position));
				intent.putExtras(bundel);
				startActivity(intent);
			}else{
				Toast.makeText(context, "开始小组对讲", 0).show();
				Intent intent=new Intent(context,GroupTalkActivity.class);
				startActivity(intent);
			}
				
				
			}
		});
	}

}
