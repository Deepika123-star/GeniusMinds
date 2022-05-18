package com.genius.minds.contest;

public class MatchContestModel {
    public MatchContestModel(int id, String contest_name, String ftemafee, String fteamwin, String steamfee, String steamwin, String fteambonus, String steambonus, String category, String matchname, String subcat, String team1, String team2, String date, String starttime, String endtime, int spot, String result, String fuser, String suser, int fjoin, int sjoin) {
        this.id = id;
        this.contest_name = contest_name;
        this.ftemafee = ftemafee;
        this.fteamwin = fteamwin;
        this.steamfee = steamfee;
        this.steamwin = steamwin;
        this.fteambonus = fteambonus;
        this.steambonus = steambonus;
        this.category = category;
        this.matchname = matchname;
        this.subcat = subcat;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.starttime = starttime;
        this.endtime = endtime;
        this.spot = spot;
        this.result = result;
        this.fuser = fuser;
        this.suser = suser;
        this.fjoin = fjoin;
        this.sjoin = sjoin;
    }

    private int id;
    private String contest_name;
    private String ftemafee;
    private String fteamwin;
    private String steamfee;
    private String steamwin;
    private String fteambonus;
    private String steambonus;
    private String category;
    private String matchname;
    private String subcat;
    private String team1;
    private String team2;
    private String date;
    private String starttime;
    private String endtime;
    private int spot;
    private String result;
    private String fuser;
    private String suser;
    private int fjoin;
    private int sjoin;


    public int getFjoin() {
        return fjoin;
    }

    public void setFjoin(int fjoin) {
        this.fjoin = fjoin;
    }

    public int getSjoin() {
        return sjoin;
    }

    public void setSjoin(int sjoin) {
        this.sjoin = sjoin;
    }



    public int getSpot() {
        return spot;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }






    public String getSuser() {
        return suser;
    }

    public void setSuser(String suser) {
        this.suser = suser;
    }



    public String getFuser() {
        return fuser;
    }

    public void setFuser(String fuser) {
        this.fuser = fuser;
    }



    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }







    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContest_name() {
        return contest_name;
    }

    public void setContest_name(String contest_name) {
        this.contest_name = contest_name;
    }

    public String getFtemafee() {
        return ftemafee;
    }

    public void setFtemafee(String ftemafee) {
        this.ftemafee = ftemafee;
    }

    public String getFteamwin() {
        return fteamwin;
    }

    public void setFteamwin(String fteamwin) {
        this.fteamwin = fteamwin;
    }

    public String getSteamfee() {
        return steamfee;
    }

    public void setSteamfee(String steamfee) {
        this.steamfee = steamfee;
    }

    public String getSteamwin() {
        return steamwin;
    }

    public void setSteamwin(String steamwin) {
        this.steamwin = steamwin;
    }

    public String getFteambonus() {
        return fteambonus;
    }

    public void setFteambonus(String fteambonus) {
        this.fteambonus = fteambonus;
    }

    public String getSteambonus() {
        return steambonus;
    }

    public void setSteambonus(String steambonus) {
        this.steambonus = steambonus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
