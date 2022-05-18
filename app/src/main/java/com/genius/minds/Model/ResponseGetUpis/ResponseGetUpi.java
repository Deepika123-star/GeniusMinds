package com.genius.minds.Model.ResponseGetUpis;

import com.google.gson.annotations.SerializedName;

public class ResponseGetUpi{

	@SerializedName("geniusbetting")
	private Geniusbetting geniusbetting;

	public void setGeniusbetting(Geniusbetting geniusbetting){
		this.geniusbetting = geniusbetting;
	}

	public Geniusbetting getGeniusbetting(){
		return geniusbetting;
	}
}