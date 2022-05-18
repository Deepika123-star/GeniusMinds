package com.genius.minds.mycontest.upcoming.Model;

public class NewUpcommingModel {
    String category,subCategory,matchDate,firstTeam,lastTeam,contestname,date,startTime;

    public NewUpcommingModel(String category, String subCategory, String matchDate, String firstTeam, String lastTeam, String contestname, String date, String startTime) {
        this.category = category;
        this.subCategory = subCategory;
        this.matchDate = matchDate;
        this.firstTeam = firstTeam;
        this.lastTeam = lastTeam;
        this.contestname = contestname;
        this.date = date;
        this.startTime = startTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getLastTeam() {
        return lastTeam;
    }

    public void setLastTeam(String lastTeam) {
        this.lastTeam = lastTeam;
    }

    public String getContestname() {
        return contestname;
    }

    public void setContestname(String contestname) {
        this.contestname = contestname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
