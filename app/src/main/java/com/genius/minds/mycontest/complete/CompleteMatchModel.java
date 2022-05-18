package com.genius.minds.mycontest.complete;

public class CompleteMatchModel {
    private int id;
    private String category;
    private String series;
    private String team1;
    private String team2;
    private String f_logo;
    private String s_logo;
    private String mdate;
    private String mtime;

    public String getMatchcode() {
        return matchcode;
    }

    public void setMatchcode(String matchcode) {
        this.matchcode = matchcode;
    }

    private String matchcode;
    public CompleteMatchModel(int id, String category, String series,String matchcode, String team1, String team2, String f_logo, String s_logo, String mdate, String mtime) {
        this.id = id;
        this.category = category;
        this.series = series;
        this.matchcode=matchcode;
        this.team1 = team1;
        this.team2 = team2;
        this.f_logo = f_logo;
        this.s_logo = s_logo;
        this.mdate = mdate;
        this.mtime = mtime;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getF_logo() {
        return f_logo;
    }

    public void setF_logo(String f_logo) {
        this.f_logo = f_logo;
    }

    public String getS_logo() {
        return s_logo;
    }

    public void setS_logo(String s_logo) {
        this.s_logo = s_logo;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }


}
