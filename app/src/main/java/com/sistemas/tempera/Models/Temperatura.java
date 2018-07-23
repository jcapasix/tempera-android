package com.sistemas.tempera.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jcapasix on 23/07/18.
 */

public class Temperatura {
    @SerializedName("_id")
    private String id;

    @SerializedName("value")
    private Integer value;

    public Temperatura(String id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
