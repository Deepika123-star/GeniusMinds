package com.genius.minds.Model.ResponseTotalPrizeMoneys;

import com.google.gson.annotations.SerializedName;

public class ResponseTotalPriizeMoney{

	@SerializedName("geniusbetting")
	private Geniusbetting geniusbetting;

	public void setGeniusbetting(Geniusbetting geniusbetting){
		this.geniusbetting = geniusbetting;
	}

	public Geniusbetting getGeniusbetting(){
		return geniusbetting;
	}
}