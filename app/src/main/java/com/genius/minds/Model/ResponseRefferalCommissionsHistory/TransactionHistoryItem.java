package com.genius.minds.Model.ResponseRefferalCommissionsHistory;

import com.google.gson.annotations.SerializedName;

public class TransactionHistoryItem{

	@SerializedName("entryFee")
	private String entryFee;

	@SerializedName("reason")
	private String reason;

	@SerializedName("amount")
	private String amount;

	@SerializedName("subCategory")
	private String subCategory;

	@SerializedName("seriesName")
	private String seriesName;

	@SerializedName("myCommission")
	private String myCommission;

	@SerializedName("contestName")
	private String contestName;

	@SerializedName("type")
	private String type;

	@SerializedName("categoryName")
	private String categoryName;

	@SerializedName("referalNameMobile")
	private String referalNameMobile;

	@SerializedName("sectionName")
	private String sectionName;

	@SerializedName("tdate")
	private String tdate;

	@SerializedName("txn_time")
	private String txnTime;

	@SerializedName("id")
	private String id;

	public void setEntryFee(String entryFee){
		this.entryFee = entryFee;
	}

	public String getEntryFee(){
		return entryFee;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return reason;
	}

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}

	public void setSubCategory(String subCategory){
		this.subCategory = subCategory;
	}

	public String getSubCategory(){
		return subCategory;
	}

	public void setSeriesName(String seriesName){
		this.seriesName = seriesName;
	}

	public String getSeriesName(){
		return seriesName;
	}

	public void setMyCommission(String myCommission){
		this.myCommission = myCommission;
	}

	public String getMyCommission(){
		return myCommission;
	}

	public void setContestName(String contestName){
		this.contestName = contestName;
	}

	public String getContestName(){
		return contestName;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setReferalNameMobile(String referalNameMobile){
		this.referalNameMobile = referalNameMobile;
	}

	public String getReferalNameMobile(){
		return referalNameMobile;
	}

	public void setSectionName(String sectionName){
		this.sectionName = sectionName;
	}

	public String getSectionName(){
		return sectionName;
	}

	public void setTdate(String tdate){
		this.tdate = tdate;
	}

	public String getTdate(){
		return tdate;
	}

	public void setTxnTime(String txnTime){
		this.txnTime = txnTime;
	}

	public String getTxnTime(){
		return txnTime;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}