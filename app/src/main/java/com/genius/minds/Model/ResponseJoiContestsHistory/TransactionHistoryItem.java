package com.genius.minds.Model.ResponseJoiContestsHistory;

import com.google.gson.annotations.SerializedName;

public class TransactionHistoryItem{

	@SerializedName("entryFee")
	private String entryFee;

	@SerializedName("sectionName")
	private String sectionName;

	@SerializedName("subCategory")
	private String subCategory;

	@SerializedName("tdate")
	private String tdate;

	@SerializedName("seriesName")
	private String seriesName;

	@SerializedName("txn_time")
	private String txnTime;

	@SerializedName("id")
	private String id;

	@SerializedName("contestName")
	private String contestName;

	@SerializedName("categoryName")
	private String categoryName;

	public void setEntryFee(String entryFee){
		this.entryFee = entryFee;
	}

	public String getEntryFee(){
		return entryFee;
	}

	public void setSectionName(String sectionName){
		this.sectionName = sectionName;
	}

	public String getSectionName(){
		return sectionName;
	}

	public void setSubCategory(String subCategory){
		this.subCategory = subCategory;
	}

	public String getSubCategory(){
		return subCategory;
	}

	public void setTdate(String tdate){
		this.tdate = tdate;
	}

	public String getTdate(){
		return tdate;
	}

	public void setSeriesName(String seriesName){
		this.seriesName = seriesName;
	}

	public String getSeriesName(){
		return seriesName;
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

	public void setContestName(String contestName){
		this.contestName = contestName;
	}

	public String getContestName(){
		return contestName;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}
}