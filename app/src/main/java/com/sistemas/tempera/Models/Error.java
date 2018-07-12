package com.sistemas.tempera.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jcapasix on 25/06/18.
 */

public class Error {

    @SerializedName("title")
    private String title;

    @SerializedName("detail")
    private Integer detail;

    public Error(String title, Integer detail) {
        this.title = title;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
    }
}
