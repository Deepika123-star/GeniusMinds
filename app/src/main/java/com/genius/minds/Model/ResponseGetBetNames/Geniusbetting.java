package com.genius.minds.Model.ResponseGetBetNames;

import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("sync_time")
	private int syncTime;

	@SerializedName("res_code")
	private String resCode;

	@SerializedName("betName")
	private String betName;

	public void setResMsg(String resMsg){
		this.resMsg = resMsg;
	}

	public String getResMsg(){
		return resMsg;
	}

	public void setSyncTime(int syncTime){
		this.syncTime = syncTime;
	}

	public int getSyncTime(){
		return syncTime;
	}

	public void setResCode(String resCode){
		this.resCode = resCode;
	}

	public String getResCode(){
		return resCode;
	}

	public void setBetName(String betName){
		this.betName = betName;
	}

	public String getBetName(){
		return betName;
	}
}