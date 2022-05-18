package com.genius.minds.Model.ResposeJoinLiveContests;

import com.google.gson.annotations.SerializedName;

public class ResponseJoinLiveContest{

	@SerializedName("geniusbetting")
	private Geniusbetting geniusbetting;

	public void setGeniusbetting(Geniusbetting geniusbetting){
		this.geniusbetting = geniusbetting;
	}

	public Geniusbetting getGeniusbetting(){
		return geniusbetting;
	}
}