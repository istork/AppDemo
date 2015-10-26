package com.wotingfm.model;

import java.io.Serializable;


public class Goods implements Serializable{
	private String has_next;
	private Goodsfy items;
	private String request_id;
	public String getHas_next() {
		return has_next;
	}
	public void setHas_next(String has_next) {
		this.has_next = has_next;
	}
	
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public Goodsfy getItems() {
		return items;
	}
	public void setItems(Goodsfy items) {
		this.items = items;
	}
	
	

	
}
