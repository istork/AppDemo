package com.xl.demo.activity.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.shenstec.activity.annotation.ViewInject;
import com.xl.demo.R;
import com.xl.demo.commactivity.BaseActivity;



public class NoYZMActivity extends BaseActivity implements OnClickListener {
	@ViewInject(id=R.id.head_left_btn,click=true)
	private LinearLayout head_left_btn;
	@ViewInject(id=R.id.lin_tel,click=true)
	private LinearLayout lin_tel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yzm_no);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_left_btn:
			finish();
			break;
		case R.id.lin_tel:
			Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"18800119521"));
			startActivity(intent);
			break;
		}
	}
}
