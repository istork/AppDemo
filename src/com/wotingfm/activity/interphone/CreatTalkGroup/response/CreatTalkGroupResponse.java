package com.wotingfm.activity.interphone.CreatTalkGroup.response;

import java.util.List;

import com.wotingfm.activity.interphone.CreatTalkGroup.model.CreatTalkGroup;
import com.wotingfm.main.response.DefaultResponse;


public class CreatTalkGroupResponse extends DefaultResponse {


	public CreatTalkGroup list;
	public CreatTalkGroupResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreatTalkGroupResponse(String REV, String userid) {
		super(REV, userid);
		// TODO Auto-generated constructor stub
	}
	public CreatTalkGroupResponse(CreatTalkGroup list) {
		// TODO Auto-generated constructor stub
		this.list=list;
	}

}
