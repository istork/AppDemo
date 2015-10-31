package com.wotingfm.activity.interphone.talkperson.service;


import com.wotingfm.activity.interphone.talkperson.dataprovider.TalkPersonProvider;
import com.wotingfm.main.IProcess.ICondition;
import com.wotingfm.main.service.DefaultService;
import android.content.Context;

public class TalkPersonService extends DefaultService {

	public TalkPersonService(Context context, ICondition condition, int taskId) {
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
		new TalkPersonProvider(context, this).sendRequest(taskId);
	}

}
