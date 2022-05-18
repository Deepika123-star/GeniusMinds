package com.genius.minds.mycontest;

public class LegerboardModel {

    private int id;
    private int contestid;
    private String winstatus;
    private String useremail;
    private String username;
    private String amount;
    private String winamount;
    private String matchcode;


    public LegerboardModel(int id, int contestid, String winstatus, String useremail, String username, String amount, String winamount,String matchcode) {
        this.id = id;
        this.contestid = contestid;
        this.winstatus = winstatus;
        this.useremail = useremail;
        this.username = username;
        this.amount = amount;
        this.winamount = winamount;
        this.matchcode = matchcode;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContestid() {
        return contestid;
    }

    public void setContestid(int contestid) {
        this.contestid = contestid;
    }

    public String getWinstatus() {
        return winstatus;
    }

    public void setWinstatus(String winstatus) {
        this.winstatus = winstatus;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWinamount() {
        return winamount;
    }

    public void setWinamount(String winamount) {
        this.winamount = winamount;
    }

     public String getMatchcode() {
        return matchcode;
    }

    public void setMatchcode(String matchcode) {
        this.matchcode = matchcode;
    }


}
