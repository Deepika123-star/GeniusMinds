package com.genius.minds.Model.ResponseMyRefralLists;

import com.google.gson.annotations.SerializedName;

public class MyreferalItem{

	@SerializedName("myreferal")
	private String myreferal;

	@SerializedName("address")
	private String address;

	@SerializedName("city")
	private String city;

	@SerializedName("bonus")
	private String bonus;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("token")
	private String token;

	@SerializedName("password")
	private String password;

	@SerializedName("name")
	private String name;

	@SerializedName("regdate")
	private String regdate;

	@SerializedName("deposit")
	private String deposit;

	@SerializedName("id")
	private String id;

	@SerializedName("state")
	private String state;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setMyreferal(String myreferal){
		this.myreferal = myreferal;
	}

	public String getMyreferal(){
		return myreferal;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setBonus(String bonus){
		this.bonus = bonus;
	}

	public String getBonus(){
		return bonus;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setRegdate(String regdate){
		this.regdate = regdate;
	}

	public String getRegdate(){
		return regdate;
	}

	public void setDeposit(String deposit){
		this.deposit = deposit;
	}

	public String getDeposit(){
		return deposit;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}