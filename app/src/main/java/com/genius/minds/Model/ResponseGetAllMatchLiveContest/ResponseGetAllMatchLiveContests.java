package com.genius.minds.Model.ResponseGetAllMatchLiveContest;

import com.google.gson.annotations.SerializedName;

public class ResponseGetAllMatchLiveContests{

	@SerializedName("geniusbetting")
	private Geniusbetting geniusbetting;

	public void setGeniusbetting(Geniusbetting geniusbetting){
		this.geniusbetting = geniusbetting;
	}

	public Geniusbetting getGeniusbetting(){
		return geniusbetting;
	}
}