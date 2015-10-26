package com.wotingfm.activity.speak;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.wotingfm.R;
import com.wotingfm.commactivity.BaseFragment;

public class BBFragment extends BaseFragment  implements OnClickListener{
	private FragmentActivity context;
	private LinearLayout lin_1;
	private LinearLayout lin_2;
	private LinearLayout lin_3;
	private LinearLayout lin_4;
	private LinearLayout lin_5;
	private LinearLayout lin_6;
	private LinearLayout lin_7;
	private LinearLayout lin_8;
	private LinearLayout lin_9;
	private LinearLayout lin_10;
	private LinearLayout lin_a;
	private LinearLayout lin_b;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_bb, container, false);
    	context = this.getActivity();
    	lin_a = (LinearLayout) rootView.findViewById(R.id.lin_a);
    	lin_b = (LinearLayout) rootView.findViewById(R.id.lin_b);
    	lin_1 = (LinearLayout) rootView.findViewById(R.id.lin_1);
    	lin_2 = (LinearLayout) rootView.findViewById(R.id.lin_2);
    	lin_3 = (LinearLayout) rootView.findViewById(R.id.lin_3);
    	lin_4 = (LinearLayout) rootView.findViewById(R.id.lin_4);
    	lin_5 = (LinearLayout) rootView.findViewById(R.id.lin_5);
    	lin_6 = (LinearLayout) rootView.findViewById(R.id.lin_6);
    	lin_7 = (LinearLayout) rootView.findViewById(R.id.lin_7);
    	lin_8 = (LinearLayout) rootView.findViewById(R.id.lin_8);
    	lin_9 = (LinearLayout) rootView.findViewById(R.id.lin_9);
    	lin_10 = (LinearLayout) rootView.findViewById(R.id.lin_10);
    	
    	lin_a.setOnClickListener(this);
    	lin_b.setOnClickListener(this);
    	lin_1.setOnClickListener(this);
    	lin_2.setOnClickListener(this);
    	lin_3.setOnClickListener(this);
    	lin_4.setOnClickListener(this);
    	lin_5.setOnClickListener(this);
    	lin_6.setOnClickListener(this);
    	lin_7.setOnClickListener(this);
    	lin_8.setOnClickListener(this);
    	lin_9.setOnClickListener(this);
    	lin_10.setOnClickListener(this);
    	return rootView;
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_a:
			startActivity(new Intent(context, SpeakAddActivity.class));
			break;
		case R.id.lin_b:
			startActivity(new Intent(context, SpeakgroupActivity.class));
			break;
		case R.id.lin_1:
			startActivity(new Intent(context, PersonSpeakActivity.class));
			break;
		case R.id.lin_2:
			startActivity(new Intent(context, PersonSpeakActivity.class));
			break;
		case R.id.lin_3:
			startActivity(new Intent(context, PersonSpeakActivity.class));
			break;
		case R.id.lin_4:
			startActivity(new Intent(context, PersonSpeakActivity.class));
			break;
		case R.id.lin_5:
			startActivity(new Intent(context, PersonSpeakActivity.class));
			break;
		case R.id.lin_6:
			startActivity(new Intent(context, PersonSpeakActivity.class));
			break;
		case R.id.lin_7:
			startActivity(new Intent(context, PersonSpeakActivity.class));
			break;
		case R.id.lin_8:
			startActivity(new Intent(context, PersonSpeakActivity.class));
			break;
		case R.id.lin_9:
			startActivity(new Intent(context, PersonSpeakActivity.class));
			break;
		case R.id.lin_10:
			startActivity(new Intent(context, PersonSpeakActivity.class));
			break;

		default:
			break;
		}
	}
    
    

}
