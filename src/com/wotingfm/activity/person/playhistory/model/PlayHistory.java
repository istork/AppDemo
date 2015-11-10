package com.wotingfm.activity.person.playhistory.model;

import java.util.List;


public class PlayHistory {
	private String ReturnType;
	private String SessionId;
	private List<PlayHistoryInside> PlayList;
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
	public List<PlayHistoryInside> getPlayList() {
		return PlayList;
	}
	public void setPlayList(List<PlayHistoryInside> playList) {
		PlayList = playList;
	}

}
