package com.wotingfm.activity.interphone.talkoldlist.response;

import java.util.List;

import com.wotingfm.activity.interphone.talkoldlist.model.TalkOldList;
import com.wotingfm.main.response.DefaultResponse;


public class TalkOldListResponse extends DefaultResponse {


	public TalkOldList list;
	public TalkOldListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TalkOldListResponse(String REV, String userid) {
		super(REV, userid);
		// TODO Auto-generated constructor stub
	}
	public TalkOldListResponse(TalkOldList list) {
		// TODO Auto-generated constructor stub
		this.list=list;
	}
	
}
