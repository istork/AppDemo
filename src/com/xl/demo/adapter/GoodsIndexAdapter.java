package com.xl.demo.adapter;

import java.util.List;

import com.shenstec.utils.image.ImageLoader;
import com.xl.demo.R;
import com.xl.demo.model.GoodsDetails;
import com.xl.demo.model.GoodsDetailsb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsIndexAdapter extends BaseAdapter{
	private Context context;
	private List<GoodsDetailsb> list;

	public GoodsIndexAdapter(Context context,List<GoodsDetailsb> list){
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
			view=LayoutInflater.from(context).inflate(R.layout.adapter_goodsindex, null);
			viewHolder.textView1=(TextView)view.findViewById(R.id.textView1);
			viewHolder.textView2=(TextView)view.findViewById(R.id.textView2);
			viewHolder.textView3=(TextView)view.findViewById(R.id.textView3);
			viewHolder.imageView=(ImageView)view.findViewById(R.id.imageView);
			view.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) view.getTag();
		}
		viewHolder.textView1.setText(list.get(position).getName());
		return view;
	}
	
	static class ViewHolder {

		public ImageView imageView;
		public TextView textView3;
		public TextView textView1;
		public TextView textView2;



	}
}
