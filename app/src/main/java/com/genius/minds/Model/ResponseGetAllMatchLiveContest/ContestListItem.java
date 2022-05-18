package com.genius.minds.Model.ResponseGetAllMatchLiveContest;

import com.google.gson.annotations.SerializedName;

public class ContestListItem{

	@SerializedName("matchcode")
	private String matchcode;

	@SerializedName("id ")
	private String id;

	@SerializedName("mdate")
	private String mdate;

	@SerializedName("fuser")
	private String fuser;

	@SerializedName("suser")
	private String suser;

	@SerializedName("series")
	private String series;

	@SerializedName("team1")
	private String team1;

	@SerializedName("f_betting_rate")
	private String fBettingRate;

	@SerializedName("team2")
	private String team2;

	@SerializedName("contest_name")
	private String contestName;

	@SerializedName("betting")
	private String betting;

	@SerializedName("s_betting_rate")
	private String sBettingRate;

	public void setMatchcode(String matchcode){
		this.matchcode = matchcode;
	}

	public String getMatchcode(){
		return matchcode;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setMdate(String mdate){
		this.mdate = mdate;
	}

	public String getMdate(){
		return mdate;
	}

	public void setFuser(String fuser){
		this.fuser = fuser;
	}

	public String getFuser(){
		return fuser;
	}

	public void setSuser(String suser){
		this.suser = suser;
	}

	public String getSuser(){
		return suser;
	}

	public void setSeries(String series){
		this.series = series;
	}

	public String getSeries(){
		return series;
	}

	public void setTeam1(String team1){
		this.team1 = team1;
	}

	public String getTeam1(){
		return team1;
	}

	public void setFBettingRate(String fBettingRate){
		this.fBettingRate = fBettingRate;
	}

	public String getFBettingRate(){
		return fBettingRate;
	}

	public void setTeam2(String team2){
		this.team2 = team2;
	}

	public String getTeam2(){
		return team2;
	}

	public void setContestName(String contestName){
		this.contestName = contestName;
	}

	public String getContestName(){
		return contestName;
	}

	public void setBetting(String betting){
		this.betting = betting;
	}

	public String getBetting(){
		return betting;
	}

	public void setSBettingRate(String sBettingRate){
		this.sBettingRate = sBettingRate;
	}

	public String getSBettingRate(){
		return sBettingRate;
	}
}