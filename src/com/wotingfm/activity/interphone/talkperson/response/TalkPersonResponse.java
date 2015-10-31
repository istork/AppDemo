package com.wotingfm.activity.interphone.talkperson.response;

import java.util.List;

import com.wotingfm.activity.interphone.talkperson.model.TalkPerson;
import com.wotingfm.activity.login.login.model.loginmessage;
import com.wotingfm.main.response.DefaultResponse;


public class TalkPersonResponse extends DefaultResponse {


	public TalkPerson list;
	public TalkPersonResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TalkPersonResponse(String REV, String userid) {
		super(REV, userid);
		// TODO Auto-generated constructor stub
	}
	public TalkPersonResponse(TalkPerson list) {
		// TODO Auto-generated constructor stub
		this.list=list;
	}
	
}
