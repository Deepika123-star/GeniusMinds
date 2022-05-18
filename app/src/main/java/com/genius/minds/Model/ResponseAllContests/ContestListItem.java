package com.genius.minds.Model.ResponseAllContests;

import com.google.gson.annotations.SerializedName;

public class ContestListItem{

	@SerializedName("date")
	private String date;

	@SerializedName("steambonus")
	private String steambonus;

	@SerializedName("id ")
	private String id;

	@SerializedName("fuser")
	private String fuser;

	@SerializedName("team1")
	private String team1;

	@SerializedName("contest_name")
	private String contestName;

	@SerializedName("team2")
	private String team2;

	@SerializedName("totalPriceMoney")
	private String totalPriceMoney;

	@SerializedName("endtime")
	private String endtime;

	@SerializedName("steamfee")
	private String steamfee;

	@SerializedName("starttime")
	private String starttime;

	@SerializedName("fteamfee")
	private String fteamfee;

	@SerializedName("fteambonus")
	private String fteambonus;

	@SerializedName("result")
	private String result;

	@SerializedName("steamwin")
	private String steamwin;

	@SerializedName("suser")
	private String suser;

	@SerializedName("fteamwin")
	private String fteamwin;

	@SerializedName("spot")
	private String spot;

	@SerializedName("sjoin")
	private String sjoin;

	@SerializedName("category")
	private String category;

	@SerializedName("fjoin")
	private String fjoin;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setSteambonus(String steambonus){
		this.steambonus = steambonus;
	}

	public String getSteambonus(){
		return steambonus;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFuser(String fuser){
		this.fuser = fuser;
	}

	public String getFuser(){
		return fuser;
	}

	public void setTeam1(String team1){
		this.team1 = team1;
	}

	public String getTeam1(){
		return team1;
	}

	public void setContestName(String contestName){
		this.contestName = contestName;
	}

	public String getContestName(){
		return contestName;
	}

	public void setTeam2(String team2){
		this.team2 = team2;
	}

	public String getTeam2(){
		return team2;
	}

	public void setTotalPriceMoney(String totalPriceMoney){
		this.totalPriceMoney = totalPriceMoney;
	}

	public String getTotalPriceMoney(){
		return totalPriceMoney;
	}

	public void setEndtime(String endtime){
		this.endtime = endtime;
	}

	public String getEndtime(){
		return endtime;
	}

	public void setSteamfee(String steamfee){
		this.steamfee = steamfee;
	}

	public String getSteamfee(){
		return steamfee;
	}

	public void setStarttime(String starttime){
		this.starttime = starttime;
	}

	public String getStarttime(){
		return starttime;
	}

	public void setFteamfee(String fteamfee){
		this.fteamfee = fteamfee;
	}

	public String getFteamfee(){
		return fteamfee;
	}

	public void setFteambonus(String fteambonus){
		this.fteambonus = fteambonus;
	}

	public String getFteambonus(){
		return fteambonus;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setSteamwin(String steamwin){
		this.steamwin = steamwin;
	}

	public String getSteamwin(){
		return steamwin;
	}

	public void setSuser(String suser){
		this.suser = suser;
	}

	public String getSuser(){
		return suser;
	}

	public void setFteamwin(String fteamwin){
		this.fteamwin = fteamwin;
	}

	public String getFteamwin(){
		return fteamwin;
	}

	public void setSpot(String spot){
		this.spot = spot;
	}

	public String getSpot(){
		return spot;
	}

	public void setSjoin(String sjoin){
		this.sjoin = sjoin;
	}

	public String getSjoin(){
		return sjoin;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setFjoin(String fjoin){
		this.fjoin = fjoin;
	}

	public String getFjoin(){
		return fjoin;
	}
}