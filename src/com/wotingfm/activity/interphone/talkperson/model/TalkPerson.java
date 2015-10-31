package com.wotingfm.activity.interphone.talkperson.model;

import java.util.List;

public class TalkPerson {
	
	private String ReturnType;
	private String SessionId;
	private List<TalkPersonInside> UserList;
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
	public List<TalkPersonInside> getUserList() {
		return UserList;
	}
	public void setUserList(List<TalkPersonInside> userList) {
		UserList = userList;
	}
	
	
	
	

}
