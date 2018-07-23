package com.sistemas.tempera.Responses;

import com.google.gson.annotations.SerializedName;
import com.sistemas.tempera.Models.Cultivo;
import com.sistemas.tempera.Models.Reporte;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcapasix on 23/07/18.
 */

public class ReportesResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("reportes")
    private List<Reporte> reportes = new ArrayList<Reporte>();

    public ReportesResponse(Boolean success, List<Reporte> reportes) {
        this.success = success;
        this.reportes = reportes;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }
}
