package com.albertmiro.pics500px.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filters {

    @SerializedName("category")
    @Expose
    private Boolean category;
    @SerializedName("exclude")
    @Expose
    private Boolean exclude;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    public Boolean getCategory() {
        return category;
    }

    public void setCategory(Boolean category) {
        this.category = category;
    }

    public Boolean getExclude() {
        return exclude;
    }

    public void setExclude(Boolean exclude) {
        this.exclude = exclude;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
