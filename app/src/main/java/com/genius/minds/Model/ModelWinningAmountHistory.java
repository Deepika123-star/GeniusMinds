package com.genius.minds.Model;

public class ModelWinningAmountHistory {
   String entryFee,winningAmount;

   public ModelWinningAmountHistory(String entryFee, String winningAmount) {
      this.entryFee = entryFee;
      this.winningAmount = winningAmount;
   }

   public String getEntryFee() {
      return entryFee;
   }

   public void setEntryFee(String entryFee) {
      this.entryFee = entryFee;
   }

   public String getWinningAmount() {
      return winningAmount;
   }

   public void setWinningAmount(String winningAmount) {
      this.winningAmount = winningAmount;
   }
}
