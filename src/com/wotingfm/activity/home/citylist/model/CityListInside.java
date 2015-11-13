package com.wotingfm.activity.home.citylist.model;

import java.io.Serializable;

public class CityListInside implements Serializable{
	
	
	private String CityId;
	private String CityName;
	private String TyPe;//是否选中1no,2yes
	public String getCityId() {
		return CityId;
	}
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getTyPe() {
		return TyPe;
	}
	public void setTyPe(String tyPe) {
		TyPe = tyPe;
	}

	
	
}
