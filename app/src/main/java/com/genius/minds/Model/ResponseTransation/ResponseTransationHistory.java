package com.genius.minds.Model.ResponseTransation;

import com.google.gson.annotations.SerializedName;

public class ResponseTransationHistory{

	@SerializedName("geniusbetting")
	private Geniusbetting geniusbetting;

	public void setGeniusbetting(Geniusbetting geniusbetting){
		this.geniusbetting = geniusbetting;
	}

	public Geniusbetting getGeniusbetting(){
		return geniusbetting;
	}
}