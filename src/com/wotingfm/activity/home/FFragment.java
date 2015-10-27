package com.wotingfm.activity.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.wotingfm.activity.play.BoFangListActivity;
import com.wotingfm.activity.play.FMBoFangActivity;
import com.wotingfm.main.commonactivity.BaseFragment;
import com.wotingfm.R;

public class FFragment extends BaseFragment  implements OnClickListener{
	private FragmentActivity context;
	private LinearLayout lin_1;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_list, container, false);
    	context = this.getActivity();
    	lin_1 = (LinearLayout) rootView.findViewById(R.id.lin_1);
    	lin_1.setOnClickListener(this);
    	return rootView;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_1:
			startActivity(new Intent(context, FMBoFangActivity.class));
			break;
		default:
			break;
	}
    
	}

}
