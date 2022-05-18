package com.genius.minds.Model.ResponseRefferalCommissionsHistory;

import com.google.gson.annotations.SerializedName;

public class ResponseRefferalCommissionHistory{

	@SerializedName("geniusbetting")
	private Geniusbetting geniusbetting;

	public void setGeniusbetting(Geniusbetting geniusbetting){
		this.geniusbetting = geniusbetting;
	}

	public Geniusbetting getGeniusbetting(){
		return geniusbetting;
	}
}