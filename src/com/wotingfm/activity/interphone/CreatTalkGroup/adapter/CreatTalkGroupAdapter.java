package com.wotingfm.activity.interphone.CreatTalkGroup.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenstec.utils.image.ImageLoader;
import com.wotingfm.R;
import com.wotingfm.activity.interphone.CreatTalkGroup.model.CreatTalkGroupInside;
import com.wotingfm.activity.interphone.talkperson.model.TalkPerson;
import com.wotingfm.main.commonactivity.GlobalConfig;

public class CreatTalkGroupAdapter extends BaseAdapter{
	private List<CreatTalkGroupInside> list;
	private Context context;
	private ImageLoader imageLoader;
	//	private OnListener onListener;
	private CreatTalkGroupInside lists;
	private String url;
	public CreatTalkGroupAdapter(Context context,List<CreatTalkGroupInside> list) {
		super();
		this.list = list;
		this.context = context;
		imageLoader=new ImageLoader(context);
	}
	public void ChangeDate(List<CreatTalkGroupInside> list){
		this.list = list;
		this.notifyDataSetChanged();
	}
	//	public void setOnListener(OnListener onListener) {
	//		this.onListener = onListener;
	//	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_creattalkgroup, null);
			holder.textView_check = (TextView)convertView.findViewById(R.id.textView_check);//名
			holder.tv_name = (TextView)convertView.findViewById(R.id.textView_name);//名
			holder.imageView_touxiang=(ImageView)convertView.findViewById(R.id.imageView);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		lists=list.get(position);
		if(lists.getUserName()==null||lists.getUserName().equals("")){
			holder.tv_name.setText("未知");//名
		}else{
			holder.tv_name.setText(lists.getUserName());//名
		}
		if(lists.getPortrait()==null||lists.getPortrait().equals("")||lists.getPortrait().equals("null")||lists.getPortrait().trim().equals("")){
			holder.imageView_touxiang.setImageResource(R.drawable.children_unchecked);
		}else{
			url=GlobalConfig.imageurl+lists.getPortrait();
			imageLoader.DisplayImage(url.replace( "\\/", "/"), holder.imageView_touxiang, false, false,null);
		}
		if(lists.getCheck()==null||
				lists.getCheck().equals("")||
				lists.getCheck().equals("null")||
				lists.getCheck().trim().equals("")||
				lists.getCheck().trim().equals("1")){
			holder.textView_check.setBackgroundResource(R.drawable.ic_detail_item_batch_download_normal);
		}else{
			holder.textView_check.setBackgroundResource(R.drawable.ic_detail_item_batch_download_pressed);

		}

		//		holder.lin_shanchu.setOnClickListener(new View.OnClickListener() {
		//
		//			public void onClick(View v) {
		//				onListener.shanchu(position);
		//			}
		//		});	
		//		
		return convertView;
	}

	//	public interface OnListener {
	//		public void shanchu(int position);
	//	}
	class ViewHolder{

		public TextView textView_check;
		public ImageView imageView_touxiang;
		public TextView tv_name;

	}
}
