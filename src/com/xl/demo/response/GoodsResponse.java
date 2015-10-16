package com.xl.demo.response;

import com.xl.demo.model.GoodsAll;

public class GoodsResponse extends GoodsDefaultResponse {

	public GoodsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public PeizhongResponse(String REV, String nickname,String classify, String kind) {
//		super(REV, nickname,classify,kind);
//		// TODO Auto-generated constructor stub
//	}
	public GoodsAll list;
	public GoodsResponse(GoodsAll list) {
		super();
		this.list = list;
	}
}

