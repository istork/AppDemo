package com.wotingfm.activity.login.login.model;

import java.util.List;

public class loginmessage {

	 private String ReturnType;
	 private String Sessionid;
	
private List<UserInfo>  UserInfo;

public String getReturnType() {
	return ReturnType;
}

public void setReturnType(String returnType) {
	ReturnType = returnType;
}

public String getSessionid() {
	return Sessionid;
}

public void setSessionid(String sessionid) {
	Sessionid = sessionid;
}

public List<UserInfo> getUserInfo() {
	return UserInfo;
}

public void setUserInfo(List<UserInfo> userInfo) {
	UserInfo = userInfo;
}




}
