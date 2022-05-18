package com.genius.minds.Model;

public class ModelContinue {
   String  playerName,points;

   public ModelContinue(String playerName, String points) {
      this.playerName = playerName;
      this.points = points;
   }

   public String getPlayerName() {
      return playerName;
   }

   public void setPlayerName(String playerName) {
      this.playerName = playerName;
   }

   public String getPoints() {
      return points;
   }

   public void setPoints(String points) {
      this.points = points;
   }
}
