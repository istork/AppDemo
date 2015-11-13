package com.wotingfm.activity.home.languagesearch.adapter;

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
import com.wotingfm.activity.home.languagesearch.model.LanguageSearchInside;

public class LanguageSearchAdapter extends BaseAdapter{
	private List<LanguageSearchInside> list;
	private Context context;
	private ImageLoader imageLoader;
	private OnListener onListener;
	private LanguageSearchInside lists;
	public LanguageSearchAdapter(Context context,List<LanguageSearchInside> list) {
		super();
		this.list = list;
		this.context = context;
		imageLoader=new ImageLoader(context);
	}
	public void ChangeDate(List<LanguageSearchInside> list){
		this.list = list;
		this.notifyDataSetChanged();
	}
	public void setOnListener(OnListener onListener) {
		this.onListener = onListener;
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
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_languagesearch, null);
			holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);//名
			holder.image_stop=(ImageView)convertView.findViewById(R.id.image_stop);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		lists=list.get(position);
		if(lists.getName()==null||lists.getName().equals("")){
			holder.tv_name.setText("未知");//名
		}else{
			holder.tv_name.setText(lists.getName());//名
		}
		//		if(lists.getGroupImg()==null||lists.getGroupImg().equals("")||lists.getGroupImg().equals("null")||lists.getGroupImg().trim().equals("")){
		//			holder.imageView_touxiang.setImageResource(R.drawable.children_unchecked);
		//		}else{
		//			String url = GlobalConfig.imageurl+lists.getGroupImg();
		//			
		//			imageLoader.DisplayImage(url.replace( "\\/", "/"), holder.imageView_touxiang, false, false,null);
		//		}

		holder.image_stop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onListener.stop(position);
			}
		});	
		return convertView;
	}

	public interface OnListener {
		public void stop(int position);
	}
	class ViewHolder{

		public ImageView image_stop;
		public TextView tv_num;
		public ImageView imageView_touxiang;
		public TextView tv_name;

	}
}
