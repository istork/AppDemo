package com.wotingfm.main.response;

public class DefaultResponse {
	public String REV;
	public String userid="-1";
	public DefaultResponse()
	{
		super();
	}
	
	public DefaultResponse(String REV,String userid)
	{
		this.REV = REV;
		this.userid = userid;
	}

}
