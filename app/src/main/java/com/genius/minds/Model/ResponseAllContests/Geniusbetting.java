package com.genius.minds.Model.ResponseAllContests;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("sync_time")
	private int syncTime;

	@SerializedName("res_code")
	private String resCode;

	@SerializedName("ContestList")
	private List<ContestListItem> contestList;

	public void setResMsg(String resMsg){
		this.resMsg = resMsg;
	}

	public String getResMsg(){
		return resMsg;
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

	public void setContestList(List<ContestListItem> contestList){
		this.contestList = contestList;
	}

	public List<ContestListItem> getContestList(){
		return contestList;
	}
}