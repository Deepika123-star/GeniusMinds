package com.genius.minds.utills;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geniusbetting {
    @SerializedName("res_code")
    @Expose
    private String resCode;
    @SerializedName("res_msg")
    @Expose
    private String resMsg;
    @SerializedName("sync_time")
    @Expose
    private Integer syncTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public Integer getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Integer syncTime) {
        this.syncTime = syncTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
