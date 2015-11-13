package com.wotingfm.activity.home.languagesearch.model;

import java.io.Serializable;

public class LanguageSearchInside implements Serializable{
	
	
	private String TyPe;
	private String Path;   
	private String Name;
	private String Image;
	
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getTyPe() {
		return TyPe;
	}
	public void setTyPe(String tyPe) {
		TyPe = tyPe;
	}
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

}
