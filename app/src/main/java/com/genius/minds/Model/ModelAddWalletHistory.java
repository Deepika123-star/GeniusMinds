package com.genius.minds.Model;


public class ModelAddWalletHistory {
   String paymentMode,Date,time,amount,status;

   public ModelAddWalletHistory(String paymentMode, String date, String time, String amount, String status) {
      this.paymentMode = paymentMode;
      Date = date;
      this.time = time;
      this.amount = amount;
      this.status = status;
   }

   public String getPaymentMode() {
      return paymentMode;
   }

   public void setPaymentMode(String paymentMode) {
      this.paymentMode = paymentMode;
   }

   public String getDate() {
      return Date;
   }

   public void setDate(String date) {
      Date = date;
   }

   public String getTime() {
      return time;
   }

   public void setTime(String time) {
      this.time = time;
   }

   public String getAmount() {
      return amount;
   }

   public void setAmount(String amount) {
      this.amount = amount;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
