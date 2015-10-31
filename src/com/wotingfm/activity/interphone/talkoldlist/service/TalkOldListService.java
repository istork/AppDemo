package com.wotingfm.activity.interphone.talkoldlist.service;


import com.wotingfm.activity.interphone.talkoldlist.dataprovider.TalkOldListProvider;
import com.wotingfm.main.IProcess.ICondition;
import com.wotingfm.main.service.DefaultService;

import android.content.Context;

public class TalkOldListService extends DefaultService {

	public TalkOldListService(Context context, ICondition condition, int taskId) {
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
		new TalkOldListProvider(context, this).sendRequest(taskId);
	}

}
