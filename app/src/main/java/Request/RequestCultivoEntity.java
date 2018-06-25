package Request;

import com.google.gson.annotations.SerializedName;

import Models.Cultivo;
import Models.CultivoEntity;

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