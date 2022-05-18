package com.genius.minds.Network;

import com.genius.minds.utills.Geniusbetting;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAMt {

    @SerializedName("geniusbetting")
    @Expose
    private Geniusbetting geniusbetting;

    public Geniusbetting getGeniusbetting() {
        return geniusbetting;
    }

    public void setGeniusbetting(Geniusbetting geniusbetting) {
        this.geniusbetting = geniusbetting;
    }
}
