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
import com.xl.demo.activity.goods.BoFangListActivity;
import com.xl.demo.activity.speak.SpeakaActivity;
import com.xl.demo.ui.BaseFragment;

public class AFragment extends BaseFragment  implements OnClickListener {
	private FragmentActivity context;
	private LinearLayout lin_1;
	private LinearLayout lin_2;
	private LinearLayout lin_3;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_a, container, false);
    	context = this.getActivity();
      	lin_1 = (LinearLayout) rootView.findViewById(R.id.lin_1);
    	lin_2 = (LinearLayout) rootView.findViewById(R.id.lin_2);
    	lin_3 = (LinearLayout) rootView.findViewById(R.id.lin_3);
    	lin_1.setOnClickListener(this);
    	lin_2.setOnClickListener(this);
    	lin_3.setOnClickListener(this);
    	return rootView;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_1:
			startActivity(new Intent(context, BoFangListActivity.class));
			break;
		case R.id.lin_2:
			startActivity(new Intent(context, BoFangListActivity.class));
			break;
		case R.id.lin_3:
			startActivity(new Intent(context, BoFangListActivity.class));
			break;

		default:
			break;
		}
	}
    
    

}
