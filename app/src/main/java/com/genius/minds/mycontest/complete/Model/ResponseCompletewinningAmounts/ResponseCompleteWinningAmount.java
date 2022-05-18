package com.genius.minds.mycontest.complete.Model.ResponseCompletewinningAmounts;

import com.google.gson.annotations.SerializedName;

public class ResponseCompleteWinningAmount{

	@SerializedName("geniusbetting")
	private Geniusbetting geniusbetting;

	public void setGeniusbetting(Geniusbetting geniusbetting){
		this.geniusbetting = geniusbetting;
	}

	public Geniusbetting getGeniusbetting(){
		return geniusbetting;
	}
}