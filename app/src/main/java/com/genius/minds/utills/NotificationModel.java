package com.genius.minds.utills;

public class NotificationModel {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNdate() {
        return ndate;
    }

    public void setNdate(String ndate) {
        this.ndate = ndate;
    }

    private String message;
    private String ndate;
    public NotificationModel(int id, String title, String message, String ndate) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.ndate = ndate;
    }


}
