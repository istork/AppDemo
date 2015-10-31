package com.wotingfm.activity.interphone.grouptalk.service;


import com.wotingfm.activity.interphone.grouptalk.dataprovider.GroupTalkProvider;
import com.wotingfm.main.IProcess.ICondition;
import com.wotingfm.main.service.DefaultService;

import android.content.Context;

public class GroupTalkService extends DefaultService {

	public GroupTalkService(Context context, ICondition condition, int taskId) {
		super(context, condition, taskId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRandomTime() {
		// TODO Auto-generated method stub
		return super.randomX;
	}

	public void sendRequest(int randomX) {
		super.randomX = randomX;
		new GroupTalkProvider(context, this).sendRequest(taskId);
	}

}
