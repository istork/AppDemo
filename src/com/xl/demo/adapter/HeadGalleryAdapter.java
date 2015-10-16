package com.xl.demo.adapter;

import java.util.List;

import com.xl.demo.R;
import com.xl.demo.model.GoodsDetailsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HeadGalleryAdapter extends BaseAdapter{
	private Context context;
	private List<GoodsDetailsa> list;

	public HeadGalleryAdapter(Context context,List<GoodsDetailsa> list){
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(view==null){
			viewHolder=new ViewHolder();
			view=LayoutInflater.from(context).inflate(R.layout.adapter_gallery, null);
			viewHolder.textView1=(TextView)view.findViewById(R.id.textView1);
			view.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) view.getTag();
		}
		viewHolder.textView1.setText(list.get(position).getName());
		return view;
	}
	
	static class ViewHolder {

		public TextView textView1;



	}
}
