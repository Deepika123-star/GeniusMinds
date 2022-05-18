package com.genius.minds.mycontest.upcoming.Model;

public class ModelViewMore {
    String serialNumber,bettingName,entryFee,rate,priceMoney;

    public ModelViewMore(String serialNumber, String bettingName, String entryFee, String rate, String priceMoney) {
        this.serialNumber = serialNumber;
        this.bettingName = bettingName;
        this.entryFee = entryFee;
        this.rate = rate;
        this.priceMoney = priceMoney;
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

    public String getPriceMoney() {
        return priceMoney;
    }

    public void setPriceMoney(String priceMoney) {
        this.priceMoney = priceMoney;
    }
}
