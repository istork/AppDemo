package com.wotingfm.activity.home.languagesearch.model;

import java.util.List;

public class LanguageSearch {
	
	private String ReturnType;
	private String SessionId;
	private List<LanguageSearchInside> GroupList;
	
	public List<LanguageSearchInside> getGroupList() {
		return GroupList;
	}
	public void setGroupList(List<LanguageSearchInside> groupList) {
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
