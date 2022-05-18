package com.genius.minds.Model.ResponseUpcomingSubCategorys;

import com.google.gson.annotations.SerializedName;

public class SubcategoryListItem{

	@SerializedName("matchcode")
	private String matchcode;

	@SerializedName("id ")
	private String id;

	@SerializedName("series")
	private String series;

	@SerializedName("team1")
	private String team1;

	@SerializedName("team2")
	private String team2;

	@SerializedName("subcat")
	private String subcat;

	@SerializedName("category")
	private String category;

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

	public void setSubcat(String subcat){
		this.subcat = subcat;
	}

	public String getSubcat(){
		return subcat;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}
}