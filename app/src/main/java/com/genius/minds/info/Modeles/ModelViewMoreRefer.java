package com.genius.minds.info.Modeles;

public class ModelViewMoreRefer {
   String serialNumber,referName,mobileNumber,joiningData,userCommission;

   public ModelViewMoreRefer(String serialNumber, String referName, String mobileNumber, String joiningData, String userCommission) {
      this.serialNumber = serialNumber;
      this.referName = referName;
      this.mobileNumber = mobileNumber;
      this.joiningData = joiningData;
      this.userCommission = userCommission;
   }

   public String getSerialNumber() {
      return serialNumber;
   }

   public void setSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
   }

   public String getReferName() {
      return referName;
   }

   public void setReferName(String referName) {
      this.referName = referName;
   }

   public String getMobileNumber() {
      return mobileNumber;
   }

   public void setMobileNumber(String mobileNumber) {
      this.mobileNumber = mobileNumber;
   }

   public String getJoiningData() {
      return joiningData;
   }

   public void setJoiningData(String joiningData) {
      this.joiningData = joiningData;
   }

   public String getUserCommission() {
      return userCommission;
   }

   public void setUserCommission(String userCommission) {
      this.userCommission = userCommission;
   }
}
