package com.genius.minds.home;

public class ModelHome {
 String catName;
int catImg;

    public ModelHome(String catName, int catImg) {
        this.catName = catName;
        this.catImg = catImg;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getCatImg() {
        return catImg;
    }

    public void setCatImg(int catImg) {
        this.catImg = catImg;
    }
}
