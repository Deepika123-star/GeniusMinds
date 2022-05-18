package com.genius.minds.Model.ResponsePrizeBreakups;

import com.google.gson.annotations.SerializedName;

public class ResponsePrizeBreakUp{

	@SerializedName("geniusbetting")
	private Geniusbetting geniusbetting;

	public void setGeniusbetting(Geniusbetting geniusbetting){
		this.geniusbetting = geniusbetting;
	}

	public Geniusbetting getGeniusbetting(){
		return geniusbetting;
	}
}