package com.genius.minds.Model.ResponseCategory;

import com.google.gson.annotations.SerializedName;

public class CategoryListItem{

	@SerializedName("image")
	private String image;

	@SerializedName("id ")
	private String id;

	@SerializedName("name")
	private String name;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}