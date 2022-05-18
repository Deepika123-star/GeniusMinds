package com.genius.minds.Model.ResponseShowMyBets;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("sync_time")
	private int syncTime;

	@SerializedName("MyBetList")
	private List<MyBetListItem> myBetList;

	@SerializedName("res_code")
	private String resCode;

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

	public void setMyBetList(List<MyBetListItem> myBetList){
		this.myBetList = myBetList;
	}

	public List<MyBetListItem> getMyBetList(){
		return myBetList;
	}

	public void setResCode(String resCode){
		this.resCode = resCode;
	}

	public String getResCode(){
		return resCode;
	}
}