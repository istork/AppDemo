package com.wotingfm.activity.interphone.CreatTalkGroup.model;

import java.util.List;

public class CreatTalkGroup {
	
	private String ReturnType;
	private String SessionId;
	private List<CreatTalkGroupInside> UserList;
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
	public List<CreatTalkGroupInside> getUserList() {
		return UserList;
	}
	public void setUserList(List<CreatTalkGroupInside> userList) {
		UserList = userList;
	}
	
	
	
	

}
