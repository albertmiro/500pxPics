package com.albertmiro.pics500px.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Default {

    @SerializedName("https")
    @Expose
    private String https;

    public String getHttps() {
        return https;
    }

    public void setHttps(String https) {
        this.https = https;
    }

}
