package com.genius.minds.Model.ResponseGetUpis;

import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("sync_time")
	private int syncTime;

	@SerializedName("Upi_id")
	private String upiId;

	@SerializedName("res_code")
	private String resCode;

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

	public void setUpiId(String upiId){
		this.upiId = upiId;
	}

	public String getUpiId(){
		return upiId;
	}

	public void setResCode(String resCode){
		this.resCode = resCode;
	}

	public String getResCode(){
		return resCode;
	}
}