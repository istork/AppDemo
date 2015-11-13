package com.wotingfm.activity.home.citylist.adapter;

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
import com.wotingfm.activity.home.citylist.model.CityListInside;
import com.wotingfm.activity.interphone.grouplist.model.TalkGroupList;
import com.wotingfm.activity.interphone.grouplist.model.TalkGroupListInside;
import com.wotingfm.activity.interphone.talkoldlist.model.TalkOldList;
import com.wotingfm.main.commonactivity.GlobalConfig;

public class CityGridAdapter extends BaseAdapter{
	private List<CityListInside> list;
	private Context context;
	private ImageLoader imageLoader;
	//	private OnListener onListener;
	private CityListInside lists;
	public CityGridAdapter(Context context,List<CityListInside> list) {
		super();
		this.list = list;
		this.context = context;
		imageLoader=new ImageLoader(context);
	}
	public void ChangeDate(List<CityListInside> list){
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
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_citylist_city, null);
			holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);//名
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		lists=list.get(position);
		if(lists.getCityName()==null||lists.getCityName().equals("")){
			holder.tv_name.setText("未知");//名
		}else{
			holder.tv_name.setText(lists.getCityName());//名
		}
		return convertView;
	}

	//	public interface OnListener {
	//		public void shanchu(int position);
	//	}
	class ViewHolder{

		public TextView tv_name;

	}
}
