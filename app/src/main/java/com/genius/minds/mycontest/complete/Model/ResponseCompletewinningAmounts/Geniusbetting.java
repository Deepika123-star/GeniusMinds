package com.genius.minds.mycontest.complete.Model.ResponseCompletewinningAmounts;

import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("referralNumber")
	private String referralNumber;

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("EntryFeeRefCommission")
	private String entryFeeRefCommission;

	@SerializedName("referralName")
	private String referralName;

	@SerializedName("sync_time")
	private int syncTime;

	@SerializedName("winningAmountRefCommission")
	private String winningAmountRefCommission;

	@SerializedName("res_code")
	private String resCode;

	public void setReferralNumber(String referralNumber){
		this.referralNumber = referralNumber;
	}

	public String getReferralNumber(){
		return referralNumber;
	}

	public void setResMsg(String resMsg){
		this.resMsg = resMsg;
	}

	public String getResMsg(){
		return resMsg;
	}

	public void setEntryFeeRefCommission(String entryFeeRefCommission){
		this.entryFeeRefCommission = entryFeeRefCommission;
	}

	public String getEntryFeeRefCommission(){
		return entryFeeRefCommission;
	}

	public void setReferralName(String referralName){
		this.referralName = referralName;
	}

	public String getReferralName(){
		return referralName;
	}

	public void setSyncTime(int syncTime){
		this.syncTime = syncTime;
	}

	public int getSyncTime(){
		return syncTime;
	}

	public void setWinningAmountRefCommission(String winningAmountRefCommission){
		this.winningAmountRefCommission = winningAmountRefCommission;
	}

	public String getWinningAmountRefCommission(){
		return winningAmountRefCommission;
	}

	public void setResCode(String resCode){
		this.resCode = resCode;
	}

	public String getResCode(){
		return resCode;
	}
}