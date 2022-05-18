package com.genius.minds.Model.ResponseUserCommissionDetails;

import com.google.gson.annotations.SerializedName;

public class CommissionDetailsItem{

	@SerializedName("date")
	private String date;

	@SerializedName("subCategory")
	private String subCategory;

	@SerializedName("RefContestWinningAmount")
	private String refContestWinningAmount;

	@SerializedName("totalPriceMoney")
	private String totalPriceMoney;

	@SerializedName("section")
	private String section;

	@SerializedName("EntryFeeCommMy")
	private String entryFeeCommMy;

	@SerializedName("contestDetails")
	private String contestDetails;

	@SerializedName("RefWinAmtMyCommi")
	private String refWinAmtMyCommi;

	@SerializedName("series")
	private String series;

	@SerializedName("RefMatchStatus")
	private String refMatchStatus;

	@SerializedName("contestFee")
	private String contestFee;

	@SerializedName("sn")
	private String sn;

	@SerializedName("category")
	private String category;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setSubCategory(String subCategory){
		this.subCategory = subCategory;
	}

	public String getSubCategory(){
		return subCategory;
	}

	public void setRefContestWinningAmount(String refContestWinningAmount){
		this.refContestWinningAmount = refContestWinningAmount;
	}

	public String getRefContestWinningAmount(){
		return refContestWinningAmount;
	}

	public void setTotalPriceMoney(String totalPriceMoney){
		this.totalPriceMoney = totalPriceMoney;
	}

	public String getTotalPriceMoney(){
		return totalPriceMoney;
	}

	public void setSection(String section){
		this.section = section;
	}

	public String getSection(){
		return section;
	}

	public void setEntryFeeCommMy(String entryFeeCommMy){
		this.entryFeeCommMy = entryFeeCommMy;
	}

	public String getEntryFeeCommMy(){
		return entryFeeCommMy;
	}

	public void setContestDetails(String contestDetails){
		this.contestDetails = contestDetails;
	}

	public String getContestDetails(){
		return contestDetails;
	}

	public void setRefWinAmtMyCommi(String refWinAmtMyCommi){
		this.refWinAmtMyCommi = refWinAmtMyCommi;
	}

	public String getRefWinAmtMyCommi(){
		return refWinAmtMyCommi;
	}

	public void setSeries(String series){
		this.series = series;
	}

	public String getSeries(){
		return series;
	}

	public void setRefMatchStatus(String refMatchStatus){
		this.refMatchStatus = refMatchStatus;
	}

	public String getRefMatchStatus(){
		return refMatchStatus;
	}

	public void setContestFee(String contestFee){
		this.contestFee = contestFee;
	}

	public String getContestFee(){
		return contestFee;
	}

	public void setSn(String sn){
		this.sn = sn;
	}

	public String getSn(){
		return sn;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}
}