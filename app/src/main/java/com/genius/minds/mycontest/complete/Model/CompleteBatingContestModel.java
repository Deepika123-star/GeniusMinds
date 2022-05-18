package com.genius.minds.mycontest.complete.Model;

import java.io.Serializable;

public class CompleteBatingContestModel implements Serializable {

    String id,series,matchcode,mdate,team1,team2,fuser,suser,contest_name,f_betting_rate,s_betting_rate,betting;

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

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
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

    public String getFuser() {
        return fuser;
    }

    public void setFuser(String fuser) {
        this.fuser = fuser;
    }

    public String getSuser() {
        return suser;
    }

    public void setSuser(String suser) {
        this.suser = suser;
    }

    public String getContest_name() {
        return contest_name;
    }

    public void setContest_name(String contest_name) {
        this.contest_name = contest_name;
    }

    public String getF_betting_rate() {
        return f_betting_rate;
    }

    public void setF_betting_rate(String f_betting_rate) {
        this.f_betting_rate = f_betting_rate;
    }

    public String getS_betting_rate() {
        return s_betting_rate;
    }

    public void setS_betting_rate(String s_betting_rate) {
        this.s_betting_rate = s_betting_rate;
    }

    public String getBetting() {
        return betting;
    }

    public void setBetting(String betting) {
        this.betting = betting;
    }
}
