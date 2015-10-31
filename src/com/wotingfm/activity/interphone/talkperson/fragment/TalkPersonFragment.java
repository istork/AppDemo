package com.wotingfm.activity.interphone.talkperson.fragment;


import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.InputFilter.AllCaps;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wotingfm.R;
import com.wotingfm.activity.interphone.SpeakAddActivity;
import com.wotingfm.activity.interphone.grouplist.activity.TalkGroupListActivity;
import com.wotingfm.activity.interphone.talkperson.adapter.TalkPersonAdapter;
import com.wotingfm.activity.interphone.talkperson.adapter.TalkPersonNoAdapter;
import com.wotingfm.activity.interphone.talkperson.model.TalkPerson;
import com.wotingfm.activity.interphone.talkperson.model.TalkPersonInside;
import com.wotingfm.activity.interphone.talkperson.response.TalkPersonResponse;
import com.wotingfm.activity.interphone.talkperson.service.TalkPersonService;
import com.wotingfm.activity.interphone.talkpersonnews.activity.TalkPersonNewsActivity;
import com.wotingfm.main.common.TaskConstant;
import com.wotingfm.main.commonactivity.BaseFragment;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;
/*
 * 对讲机-获取通讯录联系人
 */
public class TalkPersonFragment extends BaseFragment  implements OnClickListener{
	private FragmentActivity context;
	private View headview;
	private ListView listView;
	private LinearLayout head_newperson;
	private LinearLayout head_group;
	private Dialog dialog;
	private TalkPersonAdapter adapter;
	private TalkPersonNoAdapter noadapter;
	public static ArrayList<TalkPerson> ceshilist;
	private TalkPerson ceshilist1;
	private TalkPerson alllist;
	private List<TalkPersonInside> list;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_talk_person, container, false);
    	context = this.getActivity();
    	listView=(ListView)rootView.findViewById(R.id.listView);
    	//头部view
    	headview=LayoutInflater.from(context).inflate(R.layout.head_talk_person, null);
    	head_newperson = (LinearLayout) headview.findViewById(R.id.lin_newperson);
    	head_group = (LinearLayout) headview.findViewById(R.id.lin_group);
    	head_newperson.setOnClickListener(this);
    	head_group.setOnClickListener(this);
    	//添加头部view
    	listView.addHeaderView(headview);
//    	setdate();
//    	setadapter();
    	send();
    	return rootView;
    }
	
	
//	private void setadapter() {
//		// TODO Auto-generated method stub
//		adapter=new TalkPersonAdapter(context, ceshilist);
//		listView.setAdapter(adapter);
//		setlistener();
//	}
//	private void setdate() {
//	// TODO Auto-generated method stub
//	ceshilist =new ArrayList<TalkPerson>();
//	ceshilist1=new TalkPerson();
//	ceshilist1.setID("1");
//	ceshilist1.setName("小辛");
//	ceshilist.add(ceshilist1);
//	ceshilist1=new TalkPerson();
//	ceshilist1.setID("2");
//	ceshilist1.setName("小赵");
//	ceshilist.add(ceshilist1);
//	ceshilist1=new TalkPerson();
//	ceshilist1.setID("3");
//	ceshilist1.setName("小钱");
//	ceshilist.add(ceshilist1);
//	ceshilist1=new TalkPerson();
//	ceshilist1.setID("4");
//	ceshilist1.setName("小孙");
//	ceshilist.add(ceshilist1);
//	ceshilist1=new TalkPerson();
//	ceshilist1.setID("5");
//	ceshilist1.setName("小李");
//	ceshilist.add(ceshilist1);
//}
	public void send(){
		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
			dialog = Utils.Dialogph(context, "正在获取数据", dialog);
			int randomX = (int) System.currentTimeMillis();
			new TalkPersonService(context, this, TaskConstant.Task_GetTalkPerson).sendRequest(randomX);
		}else{
			ToastUtil.show_short(context, "网络失败，请检查网络");
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_newperson:
			startActivity(new Intent(context, SpeakAddActivity.class));
			break;
		case R.id.lin_group:
			startActivity(new Intent(context, TalkGroupListActivity.class));
			break;
		default:
			break;
		}
	}
    
	public void refreshUI(Message msg) {
		switch (msg.what) {
		case TaskConstant.Task_GetTalkPerson:
			if(dialog!=null){
				dialog.dismiss();
			}
			if(msg.obj!=null && msg.obj instanceof TalkPersonResponse){
				TalkPersonResponse response=(TalkPersonResponse) msg.obj;
				try {
					 alllist = response.list;
				} catch (Exception e) {
					e.printStackTrace();
				}
				alllist.getReturnType();
				alllist.getSessionId();
				list=alllist.getUserList();
				
				
				if(list==null||list.size()==0){
					ToastUtil.show_short(context, "您当前没有数据");
					noadapter=new TalkPersonNoAdapter(context);
					listView.setAdapter(noadapter);
				}else{
					adapter=new TalkPersonAdapter(context, list);
					listView.setAdapter(adapter);
					setlistener();
				}
			}else{
				ToastUtil.show_short(context, "无法获取数据");
				noadapter=new TalkPersonNoAdapter(context);
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
				Intent intent = new Intent(context,TalkPersonNewsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("data", list.get(position-1));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

}
