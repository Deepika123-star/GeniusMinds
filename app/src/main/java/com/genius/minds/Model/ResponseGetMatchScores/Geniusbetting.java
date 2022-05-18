package com.genius.minds.Model.ResponseGetMatchScores;

import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("fteamscore")
	private String fteamscore;

	@SerializedName("steamscore")
	private String steamscore;

	@SerializedName("scoreimage")
	private String scoreimage;

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

	public void setFteamscore(String fteamscore){
		this.fteamscore = fteamscore;
	}

	public String getFteamscore(){
		return fteamscore;
	}

	public void setSteamscore(String steamscore){
		this.steamscore = steamscore;
	}

	public String getSteamscore(){
		return steamscore;
	}

	public void setScoreimage(String scoreimage){
		this.scoreimage = scoreimage;
	}

	public String getScoreimage(){
		return scoreimage;
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