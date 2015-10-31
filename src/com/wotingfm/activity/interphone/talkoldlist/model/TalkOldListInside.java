package com.wotingfm.activity.interphone.talkoldlist.model;

import java.io.Serializable;

public class TalkOldListInside implements Serializable{
	
	
	private String ObjType;//类别 User，Group
	private String UserId;//
	private String GroupId;//
	private String UserName;//
	private String GroupName;//
	private String Portrait;//个人头像
	private String GroupImg;//小组图片
	public String getObjType() {
		return ObjType;
	}
	public void setObjType(String objType) {
		ObjType = objType;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getGroupId() {
		return GroupId;
	}
	public void setGroupId(String groupId) {
		GroupId = groupId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getGroupName() {
		return GroupName;
	}
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	public String getPortrait() {
		return Portrait;
	}
	public void setPortrait(String portrait) {
		Portrait = portrait;
	}
	public String getGroupImg() {
		return GroupImg;
	}
	public void setGroupImg(String groupImg) {
		GroupImg = groupImg;
	}
	
	

}
