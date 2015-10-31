package com.wotingfm.activity.interphone.grouptalk.model;

import java.io.Serializable;

public class GroupTalkInside implements Serializable{
	
	
	private String UserId;
	private String UserName;
	private String Portrait;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPortrait() {
		return Portrait;
	}
	public void setPortrait(String portrait) {
		Portrait = portrait;
	}

}
