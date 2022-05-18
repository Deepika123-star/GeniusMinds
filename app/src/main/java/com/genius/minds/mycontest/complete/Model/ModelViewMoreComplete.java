package com.genius.minds.mycontest.complete.Model;

public class ModelViewMoreComplete {
    String serialNumber,bettingName,entryFee,rate,winningPrice,result;

    public ModelViewMoreComplete(String serialNumber, String bettingName, String entryFee, String rate, String winningPrice, String result) {
        this.serialNumber = serialNumber;
        this.bettingName = bettingName;
        this.entryFee = entryFee;
        this.rate = rate;
        this.winningPrice = winningPrice;
        this.result = result;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBettingName() {
        return bettingName;
    }

    public void setBettingName(String bettingName) {
        this.bettingName = bettingName;
    }

    public String getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(String entryFee) {
        this.entryFee = entryFee;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getWinningPrice() {
        return winningPrice;
    }

    public void setWinningPrice(String winningPrice) {
        this.winningPrice = winningPrice;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
