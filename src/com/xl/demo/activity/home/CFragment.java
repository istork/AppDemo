package com.xl.demo.activity.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.xl.demo.R;
import com.xl.demo.activity.goods.BoFangBActivity;
import com.xl.demo.activity.goods.BoFangListActivity;
import com.xl.demo.activity.goods.FMBoFangActivity;
import com.xl.demo.activity.goods.FMbdOldListActivity;
import com.xl.demo.ui.BaseFragment;

public class CFragment extends BaseFragment  implements OnClickListener{
	private FragmentActivity context;
	private LinearLayout lin_a;
	private LinearLayout lin_b;
	private LinearLayout lin_c;
	private LinearLayout lin_d;
	private LinearLayout lin_1;
	private LinearLayout lin_2;
	private LinearLayout lin_more;
	private LinearLayout lin_old;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_c, container, false);
		context = this.getActivity();
		lin_a = (LinearLayout) rootView.findViewById(R.id.lin_a);
		lin_b = (LinearLayout) rootView.findViewById(R.id.lin_b);
		lin_c = (LinearLayout) rootView.findViewById(R.id.lin_c);
		lin_d = (LinearLayout) rootView.findViewById(R.id.lin_d);

		lin_1 = (LinearLayout) rootView.findViewById(R.id.lin_1);
		lin_2 = (LinearLayout) rootView.findViewById(R.id.lin_2);

		lin_more = (LinearLayout) rootView.findViewById(R.id.lin_more);
		lin_old = (LinearLayout) rootView.findViewById(R.id.lin_old);

		lin_1.setOnClickListener(this);
		lin_2.setOnClickListener(this);

		lin_more.setOnClickListener(this);
		lin_old.setOnClickListener(this);

		lin_a.setOnClickListener(this);
		lin_b.setOnClickListener(this);
		lin_c.setOnClickListener(this);
		lin_d.setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_1:
			startActivity(new Intent(context, BoFangBActivity.class));
			break;
		case R.id.lin_2:
			startActivity(new Intent(context, FMBoFangActivity.class));
			break;
			//////
		case R.id.lin_more:
			startActivity(new Intent(context, FMbdOldListActivity.class));
			break;
		case R.id.lin_old:
			startActivity(new Intent(context, FMBoFangActivity.class));
			break;
			//////
		case R.id.lin_a:
			startActivity(new Intent(context, FMListActivity.class));
			break;
		case R.id.lin_b:
			startActivity(new Intent(context, FMListActivity.class));
			break;
		case R.id.lin_c:
			startActivity(new Intent(context, CityListActivity.class));
			break;
		case R.id.lin_d:
			startActivity(new Intent(context, FMListActivity.class));
			break;
		default:
			break;
		}
	}



}
