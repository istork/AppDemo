package com.wotingfm.activity.home.citylist.model;

import java.util.List;

public class RadioList {
	
	private String ReturnType;
	private String SessionId;
	private List<RadioListInside> GroupList;
	
	public List<RadioListInside> getGroupList() {
		return GroupList;
	}
	public void setGroupList(List<RadioListInside> groupList) {
		GroupList = groupList;
	}
	public String getReturnType() {
		return ReturnType;
	}
	public void setReturnType(String returnType) {
		ReturnType = returnType;
	}
	public String getSessionId() {
		return SessionId;
	}
	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
	
	
	
	

}
