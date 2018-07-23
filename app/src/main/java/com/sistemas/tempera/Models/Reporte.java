package com.sistemas.tempera.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jcapasix on 23/07/18.
 */

public class Reporte {

    @SerializedName("_id")
    private String id;

    @SerializedName("cultivo")
    private Cultivo cultivo;

    @SerializedName("alertas")
    private Integer alertas;

    @SerializedName("temperatura")
    private Integer temperatura;

    public Reporte(String id, Cultivo cultivo, Integer alertas, Integer temperatura) {
        this.id = id;
        this.cultivo = cultivo;
        this.alertas = alertas;
        this.temperatura = temperatura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }

    public Integer getAlertas() {
        return alertas;
    }

    public void setAlertas(Integer alertas) {
        this.alertas = alertas;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }
}
