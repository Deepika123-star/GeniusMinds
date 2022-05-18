package com.genius.minds.Model.ResponseMyRefralLists;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseMyReferalList{

	@SerializedName("myreferal")
	private List<MyreferalItem> myreferal;

	@SerializedName("status")
	private int status;

	public void setMyreferal(List<MyreferalItem> myreferal){
		this.myreferal = myreferal;
	}

	public List<MyreferalItem> getMyreferal(){
		return myreferal;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}