package com.wotingfm.activity.login.welcome.fragment;



import com.wotingfm.R;
import com.wotingfm.activity.login.splash.activity.SplashActivity;
import com.wotingfm.activity.login.welcome.activity.WelcomeActivity;
import com.wotingfm.activity.main.MainActivity;
import com.wotingfm.main.common.StringConstant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

/**
 * Created with Android Studio.
 * User: ryan@xisue.com
 * Date: 7/14/14
 * Time: 11:10 PM
 * Desc: ThirdGuideFragment
 */
public class SixthGuideFragment extends BaseGuideFragment {

    View mLayoutLogo;
	private FragmentActivity context;
	private SharedPreferences sharedPreferences;
	private LinearLayout goto_world;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide_sixth, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this.getActivity();
        sharedPreferences = context.getSharedPreferences("wotingfm",Context.MODE_PRIVATE);
        mLayoutLogo = view.findViewById(R.id.guide_item_1);
        goto_world = (LinearLayout)view.findViewById(R.id.goto_world);
	       goto_world.setOnClickListener(new OnClickListener() {
 				@Override
 				public void onClick(View v) {
 					// TODO Auto-generated method stub
 					startActivity(new Intent(context, MainActivity.class));
// 					Editor edit = sharedPreferences.edit();
// 					edit.putString(StringConstant.FIRST, "1");
// 					edit.commit();
 					getActivity().finish();
 				}
 			});
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.splash_guide_logo);
        mLayoutLogo.startAnimation(anim);
    }

    @Override
    public int[] getChildViewIds() {
        return new int[]{};
    }

    @Override
    public int getRootViewId() {
        return R.id.layout_guide_sixth;
    }
}
