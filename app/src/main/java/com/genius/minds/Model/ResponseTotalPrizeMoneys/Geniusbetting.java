package com.genius.minds.Model.ResponseTotalPrizeMoneys;

import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("prizeMoney")
	private String prizeMoney;

	@SerializedName("sync_time")
	private int syncTime;

	@SerializedName("res_code")
	private String resCode;

	public void setResMsg(String resMsg){
		this.resMsg = resMsg;
	}

	public String getResMsg(){
		return resMsg;
	}

	public void setPrizeMoney(String prizeMoney){
		this.prizeMoney = prizeMoney;
	}

	public String getPrizeMoney(){
		return prizeMoney;
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
}