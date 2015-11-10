package com.wotingfm.activity.person.playhistory.adapter;

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
import com.wotingfm.activity.person.playhistory.model.PlayHistoryInside;
import com.wotingfm.main.commonactivity.GlobalConfig;

public class PlayHistoryAdapter extends BaseAdapter {
	private List<PlayHistoryInside> list;
	private Context context;
	private ImageLoader imageLoader;
	//	private OnListener onListener;
	private PlayHistoryInside lists;
	private String url;
	public PlayHistoryAdapter(Context context,List<PlayHistoryInside> list) {
		super();
		this.list = list;
		this.context = context;
		imageLoader=new ImageLoader(context);
	}
	public void ChangeDate(List<PlayHistoryInside> list){
		this.list = list;
		this.notifyDataSetChanged();
	}
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
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_play_history,null);
			holder.textView_playName = (TextView)convertView.findViewById(R.id.textView_playname);//节目名称
			holder.textView_PlayIntroduce = (TextView)convertView.findViewById(R.id.textView_playintroduce);//节目介绍
			holder.imageView_playImage=(ImageView)convertView.findViewById(R.id.imageView_playimage);//节目图片
			convertView.setTag(holder); 
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		lists=list.get(position);
		if(lists.getPlayName()==null||lists.getPlayName().equals("")){
			holder.textView_playName.setText("未知");
		}else{
			holder.textView_playName.setText(lists.getPlayName());
		}
		if(lists.getPlayImageUrl()==null||lists.getPlayImageUrl().equals("")||lists.getPlayImageUrl().equals("null")||lists.getPlayImageUrl().trim().equals("")){
			holder.imageView_playImage.setImageResource(R.drawable.children_unchecked);
		}else{
			url=GlobalConfig.imageurl+lists.getPlayImageUrl();
			imageLoader.DisplayImage(url.replace( "\\/", "/"), holder.imageView_playImage, false, false,null);
		}
	

		return convertView;
	}

	class ViewHolder{

		public TextView textView_playName;
		public TextView textView_PlayIntroduce;
		public ImageView imageView_playImage;

	}

}
