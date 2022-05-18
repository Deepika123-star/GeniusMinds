package com.genius.minds.Model.ResponseTransation;

import com.google.gson.annotations.SerializedName;

public class TransactionHistoryItem{

	@SerializedName("mode")
	private String mode;

	@SerializedName("reason")
	private String reason;

	@SerializedName("amount")
	private String amount;

	@SerializedName("tdate")
	private String tdate;

	@SerializedName("matchname")
	private String matchname;

	@SerializedName("txn_time")
	private String txnTime;

	@SerializedName("subcat")
	private String subcat;

	@SerializedName("id")
	private String id;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

	public void setMode(String mode){
		this.mode = mode;
	}

	public String getMode(){
		return mode;
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

	public void setTdate(String tdate){
		this.tdate = tdate;
	}

	public String getTdate(){
		return tdate;
	}

	public void setMatchname(String matchname){
		this.matchname = matchname;
	}

	public String getMatchname(){
		return matchname;
	}

	public void setTxnTime(String txnTime){
		this.txnTime = txnTime;
	}

	public String getTxnTime(){
		return txnTime;
	}

	public void setSubcat(String subcat){
		this.subcat = subcat;
	}

	public String getSubcat(){
		return subcat;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}