package com.genius.minds.Model;

public class ModelReferCommissionHistory {
   String  entryFeeRefer,winningAmount,userReferCommission;

   public ModelReferCommissionHistory(String entryFeeRefer, String winningAmount, String userReferCommission) {
      this.entryFeeRefer = entryFeeRefer;
      this.winningAmount = winningAmount;
      this.userReferCommission = userReferCommission;
   }

   public String getEntryFeeRefer() {
      return entryFeeRefer;
   }

   public void setEntryFeeRefer(String entryFeeRefer) {
      this.entryFeeRefer = entryFeeRefer;
   }

   public String getWinningAmount() {
      return winningAmount;
   }

   public void setWinningAmount(String winningAmount) {
      this.winningAmount = winningAmount;
   }

   public String getUserReferCommission() {
      return userReferCommission;
   }

   public void setUserReferCommission(String userReferCommission) {
      this.userReferCommission = userReferCommission;
   }
}
