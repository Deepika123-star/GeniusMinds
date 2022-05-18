package com.genius.minds.Model.ResponseShowMyBets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyBetListItem{

	@SerializedName("entryFee")
	private String entryFee;

	@SerializedName("rate")
	private String rate;

	@SerializedName("prizeMoney")
	private String prizeMoney;

	@SerializedName("referralName")
	private String referralName;

	@SerializedName("sn")
	private String sn;

	@SerializedName("winning_amount")
	private String winningAmount;

	@SerializedName("referralWinnigAmount")
	private double referralWinnigAmount;

	@SerializedName("bettingName")
	private String bettingName;
	@SerializedName("result")
	@Expose
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setEntryFee(String entryFee){
		this.entryFee = entryFee;
	}

	public String getEntryFee(){
		return entryFee;
	}

	public void setRate(String rate){
		this.rate = rate;
	}

	public String getRate(){
		return rate;
	}

	public void setPrizeMoney(String prizeMoney){
		this.prizeMoney = prizeMoney;
	}

	public String getPrizeMoney(){
		return prizeMoney;
	}

	public void setReferralName(String referralName){
		this.referralName = referralName;
	}

	public String getReferralName(){
		return referralName;
	}

	public void setSn(String sn){
		this.sn = sn;
	}

	public String getSn(){
		return sn;
	}

	public void setWinningAmount(String winningAmount){
		this.winningAmount = winningAmount;
	}

	public String getWinningAmount(){
		return winningAmount;
	}

	public void setReferralWinnigAmount(double referralWinnigAmount){
		this.referralWinnigAmount = referralWinnigAmount;
	}

	public double getReferralWinnigAmount(){
		return referralWinnigAmount;
	}

	public void setBettingName(String bettingName){
		this.bettingName = bettingName;
	}

	public String getBettingName(){
		return bettingName;
	}
}