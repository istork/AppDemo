package com.wotingfm.activity.interphone.grouplist.model;

import java.io.Serializable;

public class TalkGroupListInside implements Serializable{
	
	
	private String GroupId;
	private String GroupName;   
	private String GroupImg;
	private String GroupCount;
	
	
	public String getGroupCount() {
		return GroupCount;
	}
	public void setGroupCount(String groupCount) {
		GroupCount = groupCount;
	}
	public String getGroupId() {
		return GroupId;
	}
	public void setGroupId(String groupId) {
		GroupId = groupId;
	}
	public String getGroupName() {
		return GroupName;
	}
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	public String getGroupImg() {
		return GroupImg;
	}
	public void setGroupImg(String groupImg) {
		GroupImg = groupImg;
	}  


	
	
	

}
