package com.genius.minds.matches;

public class ModelNewHome {
    String karachi1,karachi2,Panther1,Panther2;
    int imgKarachi1,imgKarachi2,imgPanther1,imgPanther2;

    public ModelNewHome(String karachi1, String karachi2, String panther1, String panther2, int imgKarachi1, int imgKarachi2, int imgPanther1, int imgPanther2) {
        this.karachi1 = karachi1;
        this.karachi2 = karachi2;
        Panther1 = panther1;
        Panther2 = panther2;
        this.imgKarachi1 = imgKarachi1;
        this.imgKarachi2 = imgKarachi2;
        this.imgPanther1 = imgPanther1;
        this.imgPanther2 = imgPanther2;
    }


    public String getKarachi1() {
        return karachi1;
    }

    public void setKarachi1(String karachi1) {
        this.karachi1 = karachi1;
    }

    public String getKarachi2() {
        return karachi2;
    }

    public void setKarachi2(String karachi2) {
        this.karachi2 = karachi2;
    }

    public String getPanther1() {
        return Panther1;
    }

    public void setPanther1(String panther1) {
        Panther1 = panther1;
    }

    public String getPanther2() {
        return Panther2;
    }

    public void setPanther2(String panther2) {
        Panther2 = panther2;
    }

    public int getImgKarachi1() {
        return imgKarachi1;
    }

    public void setImgKarachi1(int imgKarachi1) {
        this.imgKarachi1 = imgKarachi1;
    }

    public int getImgKarachi2() {
        return imgKarachi2;
    }

    public void setImgKarachi2(int imgKarachi2) {
        this.imgKarachi2 = imgKarachi2;
    }

    public int getImgPanther1() {
        return imgPanther1;
    }

    public void setImgPanther1(int imgPanther1) {
        this.imgPanther1 = imgPanther1;
    }

    public int getImgPanther2() {
        return imgPanther2;
    }

    public void setImgPanther2(int imgPanther2) {
        this.imgPanther2 = imgPanther2;
    }
}
