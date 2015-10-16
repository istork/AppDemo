package com.xl.demo.activity.home;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xl.demo.R;
import com.xl.demo.ui.BaseFragment;

public class EFragment extends BaseFragment  {
	private FragmentActivity context;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_e, container, false);
    	context = this.getActivity();
    	return rootView;
    }
    
    

}
