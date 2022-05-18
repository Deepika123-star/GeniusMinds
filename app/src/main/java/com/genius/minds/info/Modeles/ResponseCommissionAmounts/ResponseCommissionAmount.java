package com.genius.minds.info.Modeles.ResponseCommissionAmounts;

import com.google.gson.annotations.SerializedName;

public class ResponseCommissionAmount{

	@SerializedName("Usercommission")
	private String usercommission;

	@SerializedName("ReferralWinning")
	private String referralWinning;

	@SerializedName("fteamfee")
	private String fteamfee;

	@SerializedName("status")
	private int status;

	public void setUsercommission(String usercommission){
		this.usercommission = usercommission;
	}

	public String getUsercommission(){
		return usercommission;
	}

	public void setReferralWinning(String referralWinning){
		this.referralWinning = referralWinning;
	}

	public String getReferralWinning(){
		return referralWinning;
	}

	public void setFteamfee(String fteamfee){
		this.fteamfee = fteamfee;
	}

	public String getFteamfee(){
		return fteamfee;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}