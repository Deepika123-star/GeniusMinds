package com.genius.minds.Model.ResponseSignUps;

import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("password")
	private String password;

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("referal")
	private String referal;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("otp")
	private String otp;

	@SerializedName("sync_time")
	private int syncTime;

	@SerializedName("res_code")
	private String resCode;

	@SerializedName("email")
	private String email;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setResMsg(String resMsg){
		this.resMsg = resMsg;
	}

	public String getResMsg(){
		return resMsg;
	}

	public void setReferal(String referal){
		this.referal = referal;
	}

	public String getReferal(){
		return referal;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setOtp(String otp){
		this.otp = otp;
	}

	public String getOtp(){
		return otp;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}