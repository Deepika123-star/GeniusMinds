package com.genius.minds.Model.ResponseJoiContestsHistory;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("TransactionHistory")
	private List<TransactionHistoryItem> transactionHistory;

	@SerializedName("pageindex")
	private int pageindex;

	@SerializedName("sync_time")
	private int syncTime;

	@SerializedName("res_code")
	private String resCode;

	public void setResMsg(String resMsg){
		this.resMsg = resMsg;
	}

	public String getResMsg(){
		return resMsg;
	}

	public void setTransactionHistory(List<TransactionHistoryItem> transactionHistory){
		this.transactionHistory = transactionHistory;
	}

	public List<TransactionHistoryItem> getTransactionHistory(){
		return transactionHistory;
	}

	public void setPageindex(int pageindex){
		this.pageindex = pageindex;
	}

	public int getPageindex(){
		return pageindex;
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