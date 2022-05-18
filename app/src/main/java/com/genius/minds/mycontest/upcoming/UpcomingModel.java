package com.genius.minds.mycontest.upcoming;

public class UpcomingModel {

    private int id;
    private int contestid;
    private String useremail;
    private String selectedteam;
    private String contest_name;
    private String fteamfee;
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
    private String result;
    private String toTalprizemoney;

    public UpcomingModel(int id, int contestid, String useremail, String selectedteam, String contest_name, String fteamfee, String fteamwin, String steamfee, String steamwin, String fteambonus, String steambonus, String category, String matchname, String subcat, String team1, String team2, String date, String starttime, String endtime, String result, String status, String fuser, String suser,String toTalprizemoney) {
        this.id = id;
        this.contestid = contestid;
        this.useremail = useremail;
        this.selectedteam = selectedteam;
        this.contest_name = contest_name;
        this.fteamfee = fteamfee;
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
        this.result = result;
        this.status = status;
        this.fuser = fuser;
        this.suser = suser;
        this.toTalprizemoney = toTalprizemoney;
        //this.spot = spot;
    }

    private String status;
    private String fuser;
    private String suser;
    private int spot;

    public int getSpot() {
        return spot;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }



    public String getFuser() {
        return fuser;
    }

    public void setFuser(String fuser) {
        this.fuser = fuser;
    }

    public String getSuser() {
        return suser;
    }

    public void setSuser(String suser) {
        this.suser = suser;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContestid() {
        return contestid;
    }

    public void setContestid(int contestid) {
        this.contestid = contestid;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getSelectedteam() {
        return selectedteam;
    }

    public void setSelectedteam(String selectedteam) {
        this.selectedteam = selectedteam;
    }

    public String getContest_name() {
        return contest_name;
    }

    public void setContest_name(String contest_name) {
        this.contest_name = contest_name;
    }

    public String getFteamfee() {
        return fteamfee;
    }

    public void setFteamfee(String fteamfee) {
        this.fteamfee = fteamfee;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalprizemoney() {
        return toTalprizemoney;
    }

    public void setTotalprizemoney(String toTalprizemoney) {
        this.toTalprizemoney = toTalprizemoney;
    }


}
