package com.wotingfm.activity.interphone.persontalk.activity;


import com.shenstec.utils.image.ImageLoader;
import com.wotingfm.R;
import com.wotingfm.activity.interphone.DuiJiangActivity;
import com.wotingfm.activity.interphone.persontalk.model.PublicServer;
import com.wotingfm.activity.interphone.persontalk.model.Server;
//import com.wotingfm.activity.interphone.persontalk.service.ServerConnectTask;
import com.wotingfm.activity.interphone.talkoldlist.model.TalkOldListInside;
import com.wotingfm.activity.interphone.talkperson.model.TalkPerson;
import com.wotingfm.activity.interphone.talkperson.model.TalkPersonInside;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.utils.ToastUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonTalkActivity extends Activity {
	private LinearLayout head_left_btn;
	private TalkOldListInside data;
	private ImageView image_tx;
	private TextView tv_name;
	private ImageLoader imageLoader;
	private String type;
	private ImageView image_talk;
	private TalkPersonInside datap;
	private ServerConnectHandler mConnectHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_talk_person);
		
		imageLoader=new ImageLoader(this);
		type=this.getIntent().getStringExtra("type");

		image_tx = (ImageView) findViewById(R.id.image);
		tv_name = (TextView) findViewById(R.id.tv_name);
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
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
				ToastUtil.show_long(PersonTalkActivity.this, "开始对讲连接。。。");
//				Server server = createServer();
//				ServerConnectTask connectTask = new ServerConnectTask(PersonTalkActivity.this);
//		        connectTask.execute(server);
			
			}	});
		
		
		if(type.equals("talkoldlist")){
			data=(TalkOldListInside)this.getIntent().getSerializableExtra("data");
			setview();
		}else{
			datap=(TalkPersonInside)this.getIntent().getSerializableExtra("data");
			setviewp();
		}
	}
	public Server createServer() {
		int id = -1;
		String name="我听";
		String host="192.168.1.119";
		int port=64738;
		String username="小辛";
		String password="123123";
		return new Server(id, name, host, port, username, password);
	}
  
	public static interface ServerConnectHandler {
		public void connectToServer(Server server);
		public void connectToPublicServer(PublicServer server);
	}
	private void setview() {
		// TODO Auto-generated method stub
		if(data.getUserName()==null||data.getUserName().equals("")){
			tv_name.setText("未知");//名
		}else{
			tv_name.setText(data.getUserName());//名
		}
		if(data.getPortrait()==null||data.getPortrait().equals("")||data.getPortrait().equals("null")||data.getPortrait().trim().equals("")){
			image_tx.setImageResource(R.drawable.children_unchecked);
		}else{
			String url = GlobalConfig.imageurl+data.getPortrait();
			imageLoader.DisplayImage(url.replace( "\\/", "/"), image_tx, false, false,null);
		}
	}
	private void setviewp() {
		// TODO Auto-generated method stub
		if(datap.getUserName()==null||datap.getUserName().equals("")){
			tv_name.setText("未知");//名
		}else{
			tv_name.setText(datap.getUserName());//名
		}
		if(datap.getPortrait()==null||datap.getPortrait().equals("")||datap.getPortrait().equals("null")||datap.getPortrait().trim().equals("")){
			image_tx.setImageResource(R.drawable.children_unchecked);
		}else{
			String url = GlobalConfig.imageurl+datap.getPortrait();
			imageLoader.DisplayImage(url.replace( "\\/", "/"), image_tx, false, false,null);
		}
	}



}
