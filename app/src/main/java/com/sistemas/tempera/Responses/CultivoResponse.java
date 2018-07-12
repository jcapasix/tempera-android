package com.sistemas.tempera.Responses;

import com.google.gson.annotations.SerializedName;
import com.sistemas.tempera.Models.Cultivo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcapasix on 12/07/18.
 */

public class CultivoResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("cultivo")
    private Cultivo cultivo;

    public CultivoResponse(Boolean success, Cultivo cultivo) {
        this.success = success;
        this.cultivo = cultivo;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }
}
