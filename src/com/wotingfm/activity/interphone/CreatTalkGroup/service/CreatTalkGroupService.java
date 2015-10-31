package com.wotingfm.activity.interphone.CreatTalkGroup.service;


import com.wotingfm.activity.interphone.CreatTalkGroup.dataprovider.CreatTalkGroupProvider;
import com.wotingfm.main.IProcess.ICondition;
import com.wotingfm.main.service.DefaultService;

import android.content.Context;

public class CreatTalkGroupService extends DefaultService {

	public CreatTalkGroupService(Context context, ICondition condition, int taskId) {
		super(context, condition, taskId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRandomTime() {
		// TODO Auto-generated method stub
		return super.randomX;
	}

	public void sendRequest(int randomX,String id,String userid) {
		super.randomX = randomX;
		new CreatTalkGroupProvider(context, this).sendRequest(taskId,id,userid);
	}

}
