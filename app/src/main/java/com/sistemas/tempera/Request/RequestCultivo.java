package com.sistemas.tempera.Request;

import com.google.gson.annotations.SerializedName;

import com.sistemas.tempera.Models.Cultivo;

/**
 * Created by jcapasix on 24/06/18.
 */

public class RequestCultivo {

    @SerializedName("cultivo")
    private Cultivo cultivo;

    public RequestCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }
}
