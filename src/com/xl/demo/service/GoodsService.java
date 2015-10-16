package com.xl.demo.service;

import com.xl.demo.IProcess.ICondition;
import com.xl.demo.dataprovider.GoodsProvider;

import android.content.Context;

public class GoodsService extends DefaultService {

	public GoodsService(Context context, ICondition condition, int taskId) {
		super(context, condition, taskId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRandomTime() {
		// TODO Auto-generated method stub
		return super.randomX;
	}

	public void GoodsRequest(int randomX,String seller_nick,String timestamp) {
		super.randomX = randomX;
		new GoodsProvider(context, this).GoodsRequest(taskId,seller_nick,timestamp);
	}

}
