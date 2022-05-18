package com.genius.minds.Model;

public class ModelWicketKeeper {
   String playerName,teamName,selectBy,points,Credits;

   public ModelWicketKeeper(String playerName, String teamName, String selectBy, String points, String credits) {
      this.playerName = playerName;
      this.teamName = teamName;
      this.selectBy = selectBy;
      this.points = points;
      Credits = credits;
   }

   public String getPlayerName() {
      return playerName;
   }

   public void setPlayerName(String playerName) {
      this.playerName = playerName;
   }

   public String getTeamName() {
      return teamName;
   }

   public void setTeamName(String teamName) {
      this.teamName = teamName;
   }

   public String getSelectBy() {
      return selectBy;
   }

   public void setSelectBy(String selectBy) {
      this.selectBy = selectBy;
   }

   public String getPoints() {
      return points;
   }

   public void setPoints(String points) {
      this.points = points;
   }

   public String getCredits() {
      return Credits;
   }

   public void setCredits(String credits) {
      Credits = credits;
   }
}
