package com.genius.minds.wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponse {
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
