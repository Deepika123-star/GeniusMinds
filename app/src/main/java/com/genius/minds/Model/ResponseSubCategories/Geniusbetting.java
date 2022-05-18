package com.genius.minds.Model.ResponseSubCategories;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Geniusbetting{

	@SerializedName("SubcategoryList")
	private List<SubcategoryListItem> subcategoryList;

	@SerializedName("res_msg")
	private String resMsg;

	@SerializedName("sync_time")
	private int syncTime;

	@SerializedName("res_code")
	private String resCode;

	public void setSubcategoryList(List<SubcategoryListItem> subcategoryList){
		this.subcategoryList = subcategoryList;
	}

	public List<SubcategoryListItem> getSubcategoryList(){
		return subcategoryList;
	}

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
}