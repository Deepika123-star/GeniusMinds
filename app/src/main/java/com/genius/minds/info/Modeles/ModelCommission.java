package com.genius.minds.info.Modeles;

public class ModelCommission {
    String serialNumber,date,category,series,subcategory,contestDetails,winningAmount,userCommission;

    public ModelCommission(String serialNumber, String date, String category, String series, String subcategory, String contestDetails, String winningAmount, String userCommission) {
        this.serialNumber = serialNumber;
        this.date = date;
        this.category = category;
        this.series = series;
        this.subcategory = subcategory;
        this.contestDetails = contestDetails;
        this.winningAmount = winningAmount;
        this.userCommission = userCommission;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getContestDetails() {
        return contestDetails;
    }

    public void setContestDetails(String contestDetails) {
        this.contestDetails = contestDetails;
    }

    public String getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(String winningAmount) {
        this.winningAmount = winningAmount;
    }

    public String getUserCommission() {
        return userCommission;
    }

    public void setUserCommission(String userCommission) {
        this.userCommission = userCommission;
    }
}
