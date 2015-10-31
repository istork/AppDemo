package com.wotingfm.activity.interphone.grouplist.response;

import java.util.List;

import com.wotingfm.activity.interphone.grouplist.model.TalkGroupList;
import com.wotingfm.main.response.DefaultResponse;


public class TalkGroupListResponse extends DefaultResponse {


	public TalkGroupList list;
	public TalkGroupListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TalkGroupListResponse(String REV, String userid) {
		super(REV, userid);
		// TODO Auto-generated constructor stub
	}
	public TalkGroupListResponse(TalkGroupList list) {
		// TODO Auto-generated constructor stub
		this.list=list;
	}
	
}
