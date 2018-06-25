package Responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import Models.Cultivo;
import Models.User;

/**
 * Created by jcapasix on 21/06/18.
 */

public class CultivoResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("cultivos")
    private List<Cultivo> custivos = new ArrayList<Cultivo>();

    public CultivoResponse(Boolean success, List<Cultivo> custivos) {
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
