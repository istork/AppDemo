package com.wotingfm.activity.interphone.talkperson.adapter;

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
import com.wotingfm.activity.interphone.talkperson.model.TalkPerson;
import com.wotingfm.activity.interphone.talkperson.model.TalkPersonInside;
import com.wotingfm.main.commonactivity.GlobalConfig;

public class TalkPersonAdapter extends BaseAdapter{
	private List<TalkPersonInside> list;
	private Context context;
	private ImageLoader imageLoader;
	//	private OnListener onListener;
	private TalkPersonInside lists;
	public TalkPersonAdapter(Context context,List<TalkPersonInside> list) {
		super();
		this.list = list;
		this.context = context;
		imageLoader=new ImageLoader(context);
	}
	public void ChangeDate(List<TalkPersonInside> list){
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
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_talk_person, null);
			holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);//名
			holder.imageView_touxiang=(ImageView)convertView.findViewById(R.id.image);
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
			String url = GlobalConfig.imageurl+lists.getPortrait();
			imageLoader.DisplayImage(url.replace( "\\/", "/"), holder.imageView_touxiang, false, false,null);
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

		public ImageView imageView_touxiang;
		public TextView tv_name;

	}
}
