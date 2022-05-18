package com.genius.minds.Model.ResponsePrizeBreakups;

import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("entryFeeReferralCommission")
	private String entryFeeReferralCommission;

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("userWinningAmount")
	private String userWinningAmount;

	@SerializedName("entryFeeCommission")
	private String entryFeeCommission;

	@SerializedName("sync_time")
	private int syncTime;

	@SerializedName("res_code")
	private String resCode;

	public void setEntryFeeReferralCommission(String entryFeeReferralCommission){
		this.entryFeeReferralCommission = entryFeeReferralCommission;
	}

	public String getEntryFeeReferralCommission(){
		return entryFeeReferralCommission;
	}

	public void setResMsg(String resMsg){
		this.resMsg = resMsg;
	}

	public String getResMsg(){
		return resMsg;
	}

	public void setUserWinningAmount(String userWinningAmount){
		this.userWinningAmount = userWinningAmount;
	}

	public String getUserWinningAmount(){
		return userWinningAmount;
	}

	public void setEntryFeeCommission(String entryFeeCommission){
		this.entryFeeCommission = entryFeeCommission;
	}

	public String getEntryFeeCommission(){
		return entryFeeCommission;
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