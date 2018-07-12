package com.sistemas.tempera.Request;

import com.google.gson.annotations.SerializedName;

import com.sistemas.tempera.Models.CultivoEntity;

/**
 * Created by jcapasix on 24/06/18.
 */


public class RequestCultivoEntity {

    @SerializedName("cultivo")
    private CultivoEntity cultivo;

    public RequestCultivoEntity(CultivoEntity cultivo) {
        this.cultivo = cultivo;
    }
}