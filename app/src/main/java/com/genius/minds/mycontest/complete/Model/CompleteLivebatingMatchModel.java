package com.genius.minds.mycontest.complete.Model;

import java.io.Serializable;

public class CompleteLivebatingMatchModel implements Serializable {
    String id,series,matchcode,team1,team2,f_logo,s_logo,mdate,mtime,category;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getMatchcode() {
        return matchcode;
    }

    public void setMatchcode(String matchcode) {
        this.matchcode = matchcode;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
