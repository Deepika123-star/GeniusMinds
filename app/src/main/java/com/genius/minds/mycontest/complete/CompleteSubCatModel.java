package com.genius.minds.mycontest.complete;

public class CompleteSubCatModel {

    private int id;
    private String cat_name;
    private String sub_cat;

    public CompleteSubCatModel(int id, String cat_name, String sub_cat, String matchcode, String series) {
        this.id = id;
        this.cat_name = cat_name;
        this.sub_cat = sub_cat;
        this.matchcode = matchcode;
        this.series = series;
    }

    private String matchcode;
    private String series;

    public String getMatchcode() {
        return matchcode;
    }

    public void setMatchcode(String matchcode) {
        this.matchcode = matchcode;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getSub_cat() {
        return sub_cat;
    }

    public void setSub_cat(String sub_cat) {
        this.sub_cat = sub_cat;
    }


}
