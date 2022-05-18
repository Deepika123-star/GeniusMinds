package com.genius.minds.Model.ResponseListMatchs;

import com.google.gson.annotations.SerializedName;

public class ResponseListMatch{

	@SerializedName("geniusbetting")
	private Geniusbetting geniusbetting;

	public void setGeniusbetting(Geniusbetting geniusbetting){
		this.geniusbetting = geniusbetting;
	}

	public Geniusbetting getGeniusbetting(){
		return geniusbetting;
	}
}