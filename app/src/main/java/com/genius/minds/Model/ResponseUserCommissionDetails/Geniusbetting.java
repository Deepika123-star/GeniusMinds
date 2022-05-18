package com.genius.minds.Model.ResponseUserCommissionDetails;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("CommissionDetails")
	private List<CommissionDetailsItem> commissionDetails;

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

	public void setCommissionDetails(List<CommissionDetailsItem> commissionDetails){
		this.commissionDetails = commissionDetails;
	}

	public List<CommissionDetailsItem> getCommissionDetails(){
		return commissionDetails;
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