package com.genius.minds.Model.ResponseWithDrawHistoryies;

import com.google.gson.annotations.SerializedName;

public class WidhraHistoryItem{

	@SerializedName("withdate")
	private String withdate;

	@SerializedName("phone_no")
	private String phoneNo;

	@SerializedName("withdraw_amt")
	private String withdrawAmt;

	@SerializedName("method")
	private String method;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("bank_name")
	private String bankName;

	@SerializedName("upiid")
	private String upiid;

	@SerializedName("ifsc")
	private String ifsc;

	@SerializedName("account_no")
	private String accountNo;

	@SerializedName("status")
	private String status;

	public void setWithdate(String withdate){
		this.withdate = withdate;
	}

	public String getWithdate(){
		return withdate;
	}

	public void setPhoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}

	public String getPhoneNo(){
		return phoneNo;
	}

	public void setWithdrawAmt(String withdrawAmt){
		this.withdrawAmt = withdrawAmt;
	}

	public String getWithdrawAmt(){
		return withdrawAmt;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return bankName;
	}

	public void setUpiid(String upiid){
		this.upiid = upiid;
	}

	public String getUpiid(){
		return upiid;
	}

	public void setIfsc(String ifsc){
		this.ifsc = ifsc;
	}

	public String getIfsc(){
		return ifsc;
	}

	public void setAccountNo(String accountNo){
		this.accountNo = accountNo;
	}

	public String getAccountNo(){
		return accountNo;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}