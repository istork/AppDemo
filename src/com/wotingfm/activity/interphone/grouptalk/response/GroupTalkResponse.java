package com.wotingfm.activity.interphone.grouptalk.response;

import java.util.List;

import com.wotingfm.activity.interphone.grouptalk.model.GroupTalk;
import com.wotingfm.main.response.DefaultResponse;


public class GroupTalkResponse extends DefaultResponse {


	public GroupTalk list;
	public GroupTalkResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupTalkResponse(String REV, String userid) {
		super(REV, userid);
		// TODO Auto-generated constructor stub
	}
	public GroupTalkResponse(GroupTalk list) {
		// TODO Auto-generated constructor stub
		this.list=list;
	}
	
}
