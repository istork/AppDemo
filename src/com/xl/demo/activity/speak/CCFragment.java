package com.xl.demo.activity.speak;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xl.demo.R;
import com.xl.demo.activity.login.SplashActivity;
import com.xl.demo.activity.main.MainActivity;
import com.xl.demo.ui.BaseFragment;

public class CCFragment extends BaseFragment implements OnClickListener {
	private FragmentActivity context;
	private LinearLayout lin_1;
	private LinearLayout lin_2;
//	private LinearLayout lin_1;
//	private LinearLayout lin_2;
//	private LinearLayout lin_3;
//	private LinearLayout lin_4;
//	private LinearLayout lin_5;
//	private LinearLayout lin_6;
//	private LinearLayout lin_7;
//	private LinearLayout lin_8;
//	private LinearLayout lin_9;
//	private LinearLayout lin_10;
//	private LinearLayout lin_a;
//	private LinearLayout lin_b;
//	private LinearLayout lin_more;
//	private LinearLayout lin_c;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_cc, container, false);
    	context = this.getActivity();
    	lin_1 = (LinearLayout) rootView.findViewById(R.id.lin_1);
    	lin_2 = (LinearLayout) rootView.findViewById(R.id.lin_2);
//    	lin_b = (LinearLayout) rootView.findViewById(R.id.lin_b);
//    	lin_more = (LinearLayout) rootView.findViewById(R.id.lin_more);
//    	lin_c = (LinearLayout) rootView.findViewById(R.id.lin_c);
//    	lin_6 = (LinearLayout) rootView.findViewById(R.id.lin_6);
//    	lin_7 = (LinearLayout) rootView.findViewById(R.id.lin_7);
//    	lin_8 = (LinearLayout) rootView.findViewById(R.id.lin_8);
//    	lin_9 = (LinearLayout) rootView.findViewById(R.id.lin_9);
//    	lin_10 = (LinearLayout) rootView.findViewById(R.id.lin_10);
//    	
    	lin_1.setOnClickListener(this);
    	lin_2.setOnClickListener(this);
//    	lin_more.setOnClickListener(this);
//    	lin_4.setOnClickListener(this);
//    	lin_5.setOnClickListener(this);
//    	lin_6.setOnClickListener(this);
//    	lin_7.setOnClickListener(this);
//    	lin_8.setOnClickListener(this);
//    	lin_9.setOnClickListener(this);
//    	lin_10.setOnClickListener(this);
//    	lin_b.setVisibility(View.GONE);
//		lin_a.setVisibility(View.VISIBLE);
    	return rootView;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_1:
			startActivity(new Intent(context, SpeakaActivity.class));
			break;
		case R.id.lin_2:
			startActivity(new Intent(context, SpeakActivity.class));
			break;
//		case R.id.lin_more:
//			lin_b.setVisibility(View.GONE);
//			lin_a.setVisibility(View.VISIBLE);
//			break;
//		case R.id.lin_4:
//			startActivity(new Intent(context, SpeakaActivity.class));
//			break;
//		case R.id.lin_5:
//			startActivity(new Intent(context, SpeakaActivity.class));
//			break;
//		case R.id.lin_6:
//			startActivity(new Intent(context, SpeakaActivity.class));
//			break;
//		case R.id.lin_7:
//			startActivity(new Intent(context, SpeakaActivity.class));
//			break;
//		case R.id.lin_8:
//			startActivity(new Intent(context, SpeakaActivity.class));
//			break;
//		case R.id.lin_9:
//			startActivity(new Intent(context, SpeakaActivity.class));
//			break;
//		case R.id.lin_10:
//			startActivity(new Intent(context, SpeakaActivity.class));
//			break;
//
		default:
			break;
		}
	}

    
    

}
