package com.wotingfm.activity.interphone.talkoldlist.model;

import java.util.List;

public class TalkOldList {
	
	private String ReturnType;
	private String SessionId;
	private List<TalkOldListInside> HistoryList;
	
	
	public List<TalkOldListInside> getHistoryList() {
		return HistoryList;
	}
	public void setHistoryList(List<TalkOldListInside> historyList) {
		HistoryList = historyList;
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
