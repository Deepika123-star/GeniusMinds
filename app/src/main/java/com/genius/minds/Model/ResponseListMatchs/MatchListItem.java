package com.genius.minds.Model.ResponseListMatchs;

import com.google.gson.annotations.SerializedName;

public class MatchListItem{

	@SerializedName("matchcode")
	private String matchcode;

	@SerializedName("id ")
	private String id;

	@SerializedName("mdate")
	private String mdate;

	@SerializedName("series")
	private String series;

	@SerializedName("team1")
	private String team1;

	@SerializedName("team2")
	private String team2;

	@SerializedName("mtime")
	private String mtime;

	@SerializedName("category")
	private String category;

	@SerializedName("s_logo")
	private String sLogo;

	@SerializedName("f_logo")
	private String fLogo;

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

	public void setTeam2(String team2){
		this.team2 = team2;
	}

	public String getTeam2(){
		return team2;
	}

	public void setMtime(String mtime){
		this.mtime = mtime;
	}

	public String getMtime(){
		return mtime;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setSLogo(String sLogo){
		this.sLogo = sLogo;
	}

	public String getSLogo(){
		return sLogo;
	}

	public void setFLogo(String fLogo){
		this.fLogo = fLogo;
	}

	public String getFLogo(){
		return fLogo;
	}
}