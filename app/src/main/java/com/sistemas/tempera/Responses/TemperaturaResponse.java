package com.sistemas.tempera.Responses;

import com.google.gson.annotations.SerializedName;
import com.sistemas.tempera.Models.Cultivo;
import com.sistemas.tempera.Models.Temperatura;

/**
 * Created by jcapasix on 23/07/18.
 */

public class TemperaturaResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("temperatura")
    private Temperatura temperatura;

    @SerializedName("cultivo")
    private Cultivo cultivo;

    public TemperaturaResponse(Boolean success, Temperatura temperatura, Cultivo cultivo) {
        this.success = success;
        this.temperatura = temperatura;
        this.cultivo = cultivo;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }
}
