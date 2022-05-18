package com.genius.minds.wallet;

public class TransactionModel {

    private int id;
    private String email;
    private String amount;
    private String reason;
    private String tdate;
    private int status;
    private String matchname;
    private String subcat;


    public TransactionModel(int id, String email, String amount, String reason, String tdate,int status,String matchname,String subcat) {
        this.id = id;
        this.email = email;
        this.amount = amount;
        this.reason = reason;
        this.tdate = tdate;
        this.status=status;
        this.matchname=matchname;
        this.subcat=subcat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMatchname() {
        return matchname;
    }

    public void setMatchname(String matchname) {
        this.matchname = matchname;
    }

    public String getSubcat() {
        return subcat;
    }

    public void setSubcat(String subcat) {
        this.subcat = subcat;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }
}
