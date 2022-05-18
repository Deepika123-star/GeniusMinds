package com.genius.minds.wallet;

public class InsertOrder {
    String amount;
    String customer_name;
    String customer_email;
    String customer_mobile;

    public InsertOrder(String amount, String customer_name, String customer_email, String customer_mobile) {
        this.amount = amount;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_mobile = customer_mobile;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_mobile() {
        return customer_mobile;
    }

    public void setCustomer_mobile(String customer_mobile) {
        this.customer_mobile = customer_mobile;
    }
}
