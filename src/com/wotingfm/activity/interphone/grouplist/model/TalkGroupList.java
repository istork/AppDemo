package com.wotingfm.activity.interphone.grouplist.model;

import java.util.List;

public class TalkGroupList {
	
	private String ReturnType;
	private String SessionId;
	private List<TalkGroupListInside> GroupList;
	
	public List<TalkGroupListInside> getGroupList() {
		return GroupList;
	}
	public void setGroupList(List<TalkGroupListInside> groupList) {
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
