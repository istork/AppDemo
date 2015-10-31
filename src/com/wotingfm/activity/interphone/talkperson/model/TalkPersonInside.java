package com.wotingfm.activity.interphone.talkperson.model;

import java.io.Serializable;

public class TalkPersonInside implements Serializable{
	
	private String UserId;
	private String UserName;
	private String Portrait;
	private String check="1";//1未选中2选中
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
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	

	
	
	

}
