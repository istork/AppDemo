//package com.wotingfm.activity.interphone.CreatTalkGroup.activity;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeSet;
//
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.app.PendingIntent;
//import android.content.ContentResolver;
//import android.content.ContentUris;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.provider.ContactsContract;
//import android.provider.ContactsContract.CommonDataKinds.Phone;
//import android.provider.ContactsContract.Contacts.Photo;
//import android.telephony.SmsManager;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.EditText;
//import android.widget.LinearLayout.LayoutParams;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.shenstec.activity.annotation.ViewInject;
//import com.shenstec.utils.ShensUtils;
//import com.wotingfm.R;
//import com.wotingfm.activity.interphone.SpeakgroupActivity;
//import com.wotingfm.activity.interphone.CreatTalkGroup.adapter.CreatTalkGroupAdapter;
//import com.wotingfm.activity.interphone.CreatTalkGroup.adapter.CreatTalkGroupNoAdapter;
//import com.wotingfm.activity.interphone.CreatTalkGroup.adapter.GroupRequestAdapter;
//import com.wotingfm.activity.interphone.CreatTalkGroup.model.CreatTalkGroup;
//import com.wotingfm.activity.interphone.CreatTalkGroup.response.CreatTalkGroupResponse;
//import com.wotingfm.activity.interphone.CreatTalkGroup.service.CreatTalkGroupService;
//import com.wotingfm.main.common.TaskConstant;
//import com.wotingfm.main.commonactivity.BaseActivity;
//import com.wotingfm.main.commonactivity.GlobalConfig;
//import com.wotingfm.utils.ToastUtil;
//import com.wotingfm.utils.Utils;
//
//public class GroupRequestActivity extends BaseActivity{
//	@ViewInject(id=R.id.head_left_btn,click=true)
//	private LinearLayout pubBtn;
//	@ViewInject(id=R.id.head_name_tv)
//	private TextView titleTV;
//	@ViewInject(id=R.id.head_right_btn,click=true)
//	private LinearLayout setBtn;
//	@ViewInject(id=R.id.listView)
//	private ListView listview;
//
//
//	@ViewInject(id=R.id.lin_all,click=true)
//	private TextView tv_all;
//	@ViewInject(id=R.id.et_sousuo)
//	private EditText et_sousuo;
//
//	private boolean flag = false;  
//	/**获取库Phon表字段**/  
//	private static final String[] PHONES_PROJECTION = new String[] {  
//		Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID,Phone.CONTACT_ID };  
//	/**联系人显示名称**/  
//	private static final int PHONES_DISPLAY_NAME_INDEX = 0;  
//	/**电话号码**/  
//	private static final int PHONES_NUMBER_INDEX = 1;  
//	/**头像ID**/  
//	private static final int PHONES_PHOTO_ID_INDEX = 2;  
//	/**联系人的ID**/  
//	private static final int PHONES_CONTACT_ID_INDEX = 3;  
//	private String[] indexStr = { "#", "A", "B", "C", "D", "E", "F", "G", "H",  
//			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",  
//			"V", "W", "X", "Y", "Z" };
//	private TextView tv_show;
//	private TextView textView_send;
//	private TextView textView_quxiao;
//	private AlertDialog senddialog;
//	private TextView textView_name;
//	private TextView textView_neirong;
//	private PendingIntent sentPI;
//	private String groupname;
//	private Dialog dialog;
//	private String quanxuantype;
//	private String name;
//	private ArrayList<CreatTalkGroup> searchResultList;
//	private ArrayList<CreatTalkGroup> setList;
//	private GroupRequestActivity context;
//	private View headview;
//	private LinearLayout lin_check;
//	private List<CreatTalkGroup> list;
//	//	/**联系人名称**/  
//	//	private ArrayList<String> mContactsName = new ArrayList<String>();  
//	//	/**联系人头像**/  
//	//	private ArrayList<String> mContactsNumber = new ArrayList<String>();  
//	//	/**联系人头像**/  
//	//	private ArrayList<Bitmap> mContactsPhonto = new ArrayList<Bitmap>();
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_groupadd);
//		quanxuantype="1";
////		tv_all.setText("全选");
//		name="";
//		context=this;
//		layoutIndex = (LinearLayout) this.findViewById(R.id.constact_zimu_layout);  
//		tv_show = (TextView) findViewById(R.id.tv);  
//		tv_show.setVisibility(View.GONE); 
//		searchResultList=new ArrayList<CreatTalkGroup>();
//		//头部view
//    	headview=LayoutInflater.from(context).inflate(R.layout.head_creattalkgroup, null);
//    	lin_check = (LinearLayout) headview.findViewById(R.id.lin_check);
//    	lin_check.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				startActivity(new Intent(context, SpeakgroupActivity.class));
//			}
//		});
//    	//添加头部view
//    	listview.addHeaderView(headview);
//    	getsend();
//
//
//	}
//
//	public void getsend(){
//		if(GlobalConfig.CURRENT_NETWORK_STATE_TYPE!=-1){
//			dialog = Utils.Dialogph(context, "正在获取数据", dialog);
//			int randomX = (int) System.currentTimeMillis();
//			new CreatTalkGroupService(context, this, TaskConstant.Task_CreatTalkGroup).sendRequest(randomX);
//		}else{
//			ToastUtil.show_short(context, "网络失败，请检查网络");
//		}
//	}
//	public void refreshUI(Message msg) {
//		switch (msg.what) {
//		case TaskConstant.Task_GetTalkPerson:
//			if(dialog!=null){
//				dialog.dismiss();
//			}
//			if(msg.obj!=null && msg.obj instanceof CreatTalkGroupResponse){
//				CreatTalkGroupResponse response=(CreatTalkGroupResponse) msg.obj;
//				try {
//					 list = response.list;
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				processData(list);//处理原先的数据
////				if(list==null||list.size()==0){
////					ToastUtil.show_short(context, "您当前没有数据");
////					noadapter=new CreatTalkGroupNoAdapter(context);
////					listView.setAdapter(noadapter);
////				}else{
////					adapter=new CreatTalkGroupAdapter(context, list);
////					listView.setAdapter(adapter);
////					setlistener();
////				}
//			}else{
////				ToastUtil.show_short(context, "无法获取数据");
////				noadapter=new CreatTalkGroupNoAdapter(context);
////				listView.setAdapter(noadapter);
//			}
//			break;
//
//		}
//	}
//	private void sousuo() {
//		// TODO Auto-generated method stub
//		et_sousuo.addTextChangedListener(new TextWatcher() {
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void afterTextChanged(Editable s) {
//				// TODO Auto-generated method stub
//				name=s.toString();
//				if(name==null||name.equals("")||name.trim().equals("")){
//					//关键词为空
//					if(searchResultList==null||searchResultList.size()==0){
//						Toast.makeText(context, "没有获取到数据", 0).show();
//					}else{
//						searchResultList.clear();
//						searchResultList.addAll(newPersons);
//						myAdapter.notifyDataSetChanged();
//					}
//
//				}else{
//					//关键词不为空
//					search(name);
//				}
//			}
//		});
//
//	}
//
//	private void search(final String name){
//		final Handler handler = new Handler(){
//
//			@Override
//			public void handleMessage(Message msg) {
//				// TODO Auto-generated method stub
//				super.handleMessage(msg);
//				if(searchResultList.size()==0){
//					ShensUtils.showToast("没有此人", GroupRequestActivity.this);
//					myAdapter = new GroupRequestAdapter(context,searchResultList);  
//					listview.setAdapter(myAdapter); 
//				}
//				if(myAdapter!=null){
//					myAdapter.notifyDataSetChanged();
//				}else{
//					myAdapter = new GroupRequestAdapter(context,searchResultList);  
//					listview.setAdapter(myAdapter); 
//				}
//
//			}
//
//		};
//		new Thread(){
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				super.run();
//				searchResultList.clear();
//				if(newPersons==null||newPersons.size()==0){
//				}else{
//					for(int i = 0 ;i<newPersons.size();i++){
//						if(newPersons.get(i).getName().contains(name)){
//							searchResultList.add(newPersons.get(i));
//						}
//					}
//					Message msg=new Message();
//					msg.what=0;
//					handler.sendMessage(msg);
//				}
//
//			}
//
//		}.start();
//	}
//
//	private void setlistener() {
//		// TODO Auto-generated method stub
//		listview.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				if(searchResultList.get(position).getCheck().equals("1")){
//					searchResultList.get(position).setCheck("2");
//				}else{
//					searchResultList.get(position).setCheck("1");
//				}
//
//
//				myAdapter.notifyDataSetChanged();
//			}
//		});
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		super.onClick(v);
//		switch (v.getId()) {
//		case R.id.head_left_btn:
//			finish();
//			break;
//		case R.id.head_right_btn:
//			setList=new ArrayList<CreatTalkGroup>();
//			for(int i=0;i<searchResultList.size();i++){
//				if(searchResultList.get(i).getCheck()==null){
//
//				}else{
//					if(searchResultList.get(i).getCheck().equals("2")){
//						setList.add(searchResultList.get(i));
//					}
//
//				}
//			}
//			if(setList==null||setList.size()==0){
//				ToastUtil.show_short(context, "您当前还未选择联系人");
//			}else{
////				dialog();
//			}
//			
//			
//			
//			break;
//		case R.id.lin_all:
//			if(quanxuantype.equals("1")){
//				quanxuan();
//			}else{
//				quanbuxuan();
//			}
//
//			break;
//
//
//		default:
//			break;
//		}
//	}
//
//
//
//	private void quanbuxuan() {
//		// TODO Auto-generated method stub
//		for(int i=0;i<searchResultList.size();i++){
//			if(searchResultList.get(i).getCheck()==null){
//
//			}else{
//				searchResultList.get(i).setCheck("1");
//			}
//		}
//		myAdapter.notifyDataSetChanged();
//		quanxuantype="1";
////		tv_all.setText("全选");
//	}
//
//
//
//
//
//
//	private void quanxuan() {
//		// TODO Auto-generated method stub
//		for(int i=0;i<searchResultList.size();i++){
//			if(searchResultList.get(i).getCheck()==null){
//
//			}else{
//				searchResultList.get(i).setCheck("2");
//			}
//		}
//		myAdapter.notifyDataSetChanged();
//		quanxuantype="2";
////		tv_all.setText("全不选");
//	}
//
//
//
//	@SuppressLint("NewApi")
////	private void dialog() {
////		// TODO Auto-generated method stub
////		getLayoutInflater();
////		//从这里弹出信息提示框
////		View vvv = LayoutInflater.from(this).inflate(R.layout.dialog_sendrequest,null);
////		textView_send=(TextView)vvv.findViewById(R.id.textView_send);
////		textView_quxiao=(TextView)vvv.findViewById(R.id.textView_quxiao);
////
////
////		textView_name=(TextView)vvv.findViewById(R.id.textView_name);
////		textView_neirong=(TextView)vvv.findViewById(R.id.textView_neirong);
////		textView_name.setText("");
////		for(int i=0;i<setList.size();i++){
////			if(setList.get(i).getCheck()==null){
////
////			}else{
////				if(setList.get(i).getCheck().equals("2")){
////					if(setList.get(i).getName()==null){
////
////					}else{
////						textView_name.append(setList.get(i).getName()+";");
////					}
////				}
////
////			}
////		}
////		textView_name.setText(textView_name.getText().toString()+"共"+setList.size()+"人");
////		textView_neirong.setText(GlobalConfig.userName+"邀请您加入"+groupname+"小组."+"\n下载安装养殖日历app请点击:http://www.ym18.cn/appupgrade/yangzhirili.apk");
////
////		textView_send.setOnClickListener(new OnClickListener() {
////
////			@Override
////			public void onClick(View v) {
////				// TODO Auto-generated method stub
////				send();
////				senddialog.dismiss();
////			}
////		});
////		textView_quxiao.setOnClickListener(new OnClickListener() {
////
////			@Override
////			public void onClick(View v) {
////				// TODO Auto-generated method stub
////				senddialog.dismiss();
////			}
////		});
////		senddialog=new AlertDialog.Builder(context).
////				setView(vvv).
////				//设定确定按钮的监听
////				setInverseBackgroundForced(true).
////				create();
////		senddialog.setCancelable(false);
////		senddialog.show();
////
////	}
////	protected void send() {
////		// TODO Auto-generated method stub
////		dialog = Utils.Dialogph(context, "正在发送短信", dialog);
////		for(int i=0;i<setList.size();i++){
////			if(setList.get(i).getCheck()==null){
////
////			}else{
////				if(setList.get(i).getCheck().equals("2")){
////					if(setList.get(i).getNum()==null){
////
////					}else{
////						SmsManager smsManager = SmsManager.getDefault();
////						String strContent =GlobalConfig.userName+"邀请您加入"+groupname+"小组."+"\n下载安装养殖日历app请点击:http://www.ym18.cn/appupgrade/yangzhirili.apk";
////						if (strContent.length() > 70) {
////							ArrayList<String> msgs = smsManager.divideMessage(strContent);
////							ArrayList<PendingIntent> sentIntents =  new ArrayList<PendingIntent>();
////							for (int a = 0;a<msgs.size();a++) {
////								sentIntents.add(sentPI);
////							}
////							smsManager.sendMultipartTextMessage(setList.get(i).getNum(), null, msgs,null, null);
////
////						} else {
////							smsManager.sendTextMessage(setList.get(i).getNum(), null,strContent, sentPI, null);
////						}
////					}
////				}
////
////			}
////		}
////
////		if(dialog!=null){
////			dialog.dismiss();
////		}
////		ToastUtil.show_short(context, "发送成功");
////		quanbuxuan();
////	}
//	/**
//	 * 处理数据
//	 */
//	private ArrayList<CreatTalkGroup> newPersons;
//	private HashMap<String, Integer> selector;// 存放含有索引字母的位置  
//	private LinearLayout layoutIndex; 
//	private int height;// 字体高度  
//	private GroupRequestAdapter myAdapter;
//	private void processData(List<CreatTalkGroup> persons){
//		newPersons=new ArrayList<CreatTalkGroup>();
//		String[] allNames = sortIndex(persons);  
//		sortList(allNames);  
//		selector = new HashMap<String, Integer>();  
//
//		for (int j = 0; j < indexStr.length; j++) {// 循环字母表，找出newPersons中对应字母的位置  
//			for (int i = 0; i < newPersons.size(); i++) {  
//				if (newPersons.get(i).getName().equals(indexStr[j])) {  
//					selector.put(indexStr[j], i);  
//				}  
//			}  
//
//		}  
//
//		searchResultList.addAll(newPersons);
//		myAdapter = new GroupRequestAdapter(this,searchResultList);  
//		listview.setAdapter(myAdapter); 
//		setlistener();
//		sousuo();
//		height = layoutIndex.getMeasuredHeight()/indexStr.length;  
//		getIndexView();
//	}
//	
//	@Override  
//	public void onWindowFocusChanged(boolean hasFocus) {  
////		 在oncreate里面执行下面的代码没反应，因为oncreate里面得到的getHeight=0  
//		        if (!flag) {// 这里为什么要设置个flag进行标记，我这里不先告诉你们，请读者研究，因为这对你们以后的开发有好处  
//		            height = layoutIndex.getMeasuredHeight() / indexStr.length;  
//		            getIndexView();  
//		            flag = true;  
//		        }  
//	}  
//	/** 
//	 * 获取排序后的新数据 
//	 *  
//	 * @param persons2 
//	 * @return 
//	 */  
//	public String[] sortIndex(List<CreatTalkGroup> CreatTalkGroup) {  
//		TreeSet<String> set = new TreeSet<String>();  
//		// 获取初始化数据源中的首字母，添加到set中  
//		for (CreatTalkGroup person : CreatTalkGroup) {  
//			set.add(Utils.getPinYinHeadChar(person.getName()).substring(  
//					0, 1));  
//		}  
//		// 新数组的长度为原数据加上set的大小  
//		String[] names = new String[CreatTalkGroup.size() + set.size()];  
//		int i = 0;  
//		for (String string : set) {  
//			names[i] = string;  
//			i++;  
//		}  
//		Map<String,String> pinyin=new HashMap<String,String>();
//		String[] Names = new String[CreatTalkGroup.size()];  
//		for (int j = 0; j < CreatTalkGroup.size(); j++) { 
//			String pyna=  
//					Utils  
//					.getPingYin(CreatTalkGroup.get(j).getName().toString());
//			if(pinyin.containsValue(pyna)){
//				pyna=pyna+j;
//			}
//			pinyin.put(CreatTalkGroup.get(j).getName(), pyna);
//			CreatTalkGroup.get(j).setName(pyna);  
//			Names[j] = pyna;  
//		}  
//		// 将原数据拷贝到新数据中  
//		System.arraycopy(Names, 0, names, set.size(), Names.length);  
//		// 自动按照首字母排序  
//		Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);  
//		return names;  
//	}  
//	/** 
//	 * 重新排序获得一个新的List集合 
//	 *  
//	 * @param allNames 
//	 */  
//	private void sortList(String[] allNames) {  
//		for (int i = 0; i < allNames.length; i++) {  
//			if (allNames[i].length() != 1) {  
//				for (int j = 0; j < list.size(); j++) {  
//					if (allNames[i].equals(list.get(j).getName())) {  
//						CreatTalkGroup p = list.get(j); 
//						//                        p.setName(persons.get(j).getName());
//						p.setName(list  
//								.get(j).getName());
//						newPersons.add(p);  
//					}  
//				}  
//			} else {  
//				CreatTalkGroup p = new CreatTalkGroup();  
//				p.setName(allNames[i]);
//				newPersons.add(p);  
//			}  
//		}  
//	}
//
//	/** 
//	 * 绘制索引列表 
//	 */  
//	public void getIndexView() {  
//		LinearLayout.LayoutParams params = new LayoutParams(  
//				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);  
//		for (int i = 0; i < indexStr.length; i++) {  
//			final TextView tv = new TextView(this);  
//			tv.setLayoutParams(params);  
//			tv.setText(indexStr[i]); 
//			tv.setTextSize(14);
////			tv.setTextColor(this.getResources().getColor(R.color.blue));;
//			//            tv.setBackgroundResource(R.drawable.layout_bg);
//			tv.setPadding(10, 0, 10, 0);  
//			layoutIndex.addView(tv);  
//			layoutIndex.setOnTouchListener(new OnTouchListener() {  
//
//				@Override  
//				public boolean onTouch(View v, MotionEvent event)  
//
//				{  
//					float y = event.getY();  
//					int index = (int) (y / height);  
//					if (index > -1 && index < indexStr.length) {// 防止越界  
//						String key = indexStr[index];  
//						if (selector.containsKey(key)) {  
//							int pos = selector.get(key);  
//							if (listview.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。  
//								listview.setSelectionFromTop(  
//										pos + listview.getHeaderViewsCount(), 0);  
//							} else {  
//								listview.setSelectionFromTop(pos, 0);// 滑动到第一项  
//							}  
//							tv_show.setVisibility(View.VISIBLE);  
//							tv_show.setText(indexStr[index]);  
//						}  
//					}  
//
//					switch (event.getAction()) {  
//					case MotionEvent.ACTION_DOWN:  
//						layoutIndex.setBackgroundColor(Color  
//								.parseColor("#606060")); 
//						break;  
//
//					case MotionEvent.ACTION_MOVE:  
//
//						break;  
//					case MotionEvent.ACTION_UP:  
//						layoutIndex.setBackgroundColor(Color  
//								.parseColor("#00ffffff"));  
//						tv_show.setVisibility(View.GONE);  
//						break;  
//					}  
//					return true;  
//				}  
//			});  
//		}  
//	}  
//}
