package com.wotingfm.activity.home;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wotingfm.R;
import com.wotingfm.main.commonactivity.BaseFragment;

public class DFragment extends BaseFragment  {
	private FragmentActivity context;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_d, container, false);
    	context = this.getActivity();
    	return rootView;
    }
    
    

}
