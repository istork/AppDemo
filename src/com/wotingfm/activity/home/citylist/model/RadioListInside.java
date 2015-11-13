package com.wotingfm.activity.home.citylist.model;

import java.io.Serializable;

public class RadioListInside implements Serializable{
	
	
	private String RadioId;//播放id
	private String RadioName;//播放名称
	private String RadioNews;//播放简介
	private String RadioImage;//播放图片
	private String RadioPath;//播放路径
	public String getRadioId() {
		return RadioId;
	}
	public void setRadioId(String radioId) {
		RadioId = radioId;
	}
	public String getRadioName() {
		return RadioName;
	}
	public void setRadioName(String radioName) {
		RadioName = radioName;
	}
	public String getRadioNews() {
		return RadioNews;
	}
	public void setRadioNews(String radioNews) {
		RadioNews = radioNews;
	}
	public String getRadioImage() {
		return RadioImage;
	}
	public void setRadioImage(String radioImage) {
		RadioImage = radioImage;
	}
	public String getRadioPath() {
		return RadioPath;
	}
	public void setRadioPath(String radioPath) {
		RadioPath = radioPath;
	}

	
	
	

}
