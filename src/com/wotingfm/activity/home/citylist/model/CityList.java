package com.wotingfm.activity.home.citylist.model;

import java.util.List;

public class CityList {
	
	private String ReturnType;
	private String SessionId;
	private List<CityListInside> GroupList;
	
	public List<CityListInside> getGroupList() {
		return GroupList;
	}
	public void setGroupList(List<CityListInside> groupList) {
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
