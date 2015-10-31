package com.wotingfm.activity.interphone.grouplist.service;

import com.wotingfm.activity.interphone.grouplist.dataprovider.TalkGroupListProvider;
import com.wotingfm.main.IProcess.ICondition;
import com.wotingfm.main.service.DefaultService;

import android.content.Context;

public class TalkGroupListService extends DefaultService {

	public TalkGroupListService(Context context, ICondition condition, int taskId) {
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
		new TalkGroupListProvider(context, this).sendRequest(taskId);
	}

}
