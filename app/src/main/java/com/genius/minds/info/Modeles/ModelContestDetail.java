package com.genius.minds.info.Modeles;

public class ModelContestDetail {
   String contestName,entryFee;

   public ModelContestDetail(String contestName, String entryFee) {
      this.contestName = contestName;
      this.entryFee = entryFee;
   }

   public String getContestName() {
      return contestName;
   }

   public void setContestName(String contestName) {
      this.contestName = contestName;
   }

   public String getEntryFee() {
      return entryFee;
   }

   public void setEntryFee(String entryFee) {
      this.entryFee = entryFee;
   }
}
