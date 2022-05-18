package com.genius.minds.mycontest.live.Adapter.ModelLive;

public class LiveModel {
    String categoryScore,subCategoryScore,matchDateScore,firstTeamNameScore,lastTeamNameScore,contestnameScore,dateScore,ViewFullRecord;

    public LiveModel(String categoryScore, String subCategoryScore, String matchDateScore, String firstTeamNameScore, String lastTeamNameScore, String contestnameScore, String dateScore, String viewFullRecord) {
        this.categoryScore = categoryScore;
        this.subCategoryScore = subCategoryScore;
        this.matchDateScore = matchDateScore;
        this.firstTeamNameScore = firstTeamNameScore;
        this.lastTeamNameScore = lastTeamNameScore;
        this.contestnameScore = contestnameScore;
        this.dateScore = dateScore;
        ViewFullRecord = viewFullRecord;
    }

    public String getCategoryScore() {
        return categoryScore;
    }

    public void setCategoryScore(String categoryScore) {
        this.categoryScore = categoryScore;
    }

    public String getSubCategoryScore() {
        return subCategoryScore;
    }

    public void setSubCategoryScore(String subCategoryScore) {
        this.subCategoryScore = subCategoryScore;
    }

    public String getMatchDateScore() {
        return matchDateScore;
    }

    public void setMatchDateScore(String matchDateScore) {
        this.matchDateScore = matchDateScore;
    }

    public String getFirstTeamNameScore() {
        return firstTeamNameScore;
    }

    public void setFirstTeamNameScore(String firstTeamNameScore) {
        this.firstTeamNameScore = firstTeamNameScore;
    }

    public String getLastTeamNameScore() {
        return lastTeamNameScore;
    }

    public void setLastTeamNameScore(String lastTeamNameScore) {
        this.lastTeamNameScore = lastTeamNameScore;
    }

    public String getContestnameScore() {
        return contestnameScore;
    }

    public void setContestnameScore(String contestnameScore) {
        this.contestnameScore = contestnameScore;
    }

    public String getDateScore() {
        return dateScore;
    }

    public void setDateScore(String dateScore) {
        this.dateScore = dateScore;
    }

    public String getViewFullRecord() {
        return ViewFullRecord;
    }

    public void setViewFullRecord(String viewFullRecord) {
        ViewFullRecord = viewFullRecord;
    }
}
