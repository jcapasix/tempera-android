package com.sistemas.tempera.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import com.sistemas.tempera.Models.Cultivo;

/**
 * Created by jcapasix on 21/06/18.
 */

public class CultivosResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("cultivos")
    private List<Cultivo> custivos = new ArrayList<Cultivo>();

    public CultivosResponse(Boolean success, List<Cultivo> custivos) {
        this.success = success;
        this.custivos = custivos;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Cultivo> getCustivos() {
        return custivos;
    }

    public void setCustivos(List<Cultivo> custivos) {
        this.custivos = custivos;
    }
}
