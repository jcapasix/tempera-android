package Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jcapasix on 21/06/18.
 */

public class Cultivo {

    @SerializedName("_id")
    private String id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("temperaturaMax")
    private Integer temperaturaMax;

    @SerializedName("temperaturaMin")
    private Integer temperaturaMin;

    @SerializedName("fechaInicial")
    private String fechaInicial;

    @SerializedName("fechaFinal")
    private String fechaFinal;

    public Cultivo(String id, String nombre, Integer temperaturaMax, Integer temperaturaMin, String fechaInicial, String fechaFinal) {
        this.id = id;
        this.nombre = nombre;
        this.temperaturaMax = temperaturaMax;
        this.temperaturaMin = temperaturaMin;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public Cultivo(String nombre, Integer temperaturaMax, Integer temperaturaMin, String fechaInicial, String fechaFinal) {
        this.id = "";
        this.nombre = nombre;
        this.temperaturaMax = temperaturaMax;
        this.temperaturaMin = temperaturaMin;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTemperaturaMax() {
        return temperaturaMax;
    }

    public String getTemperaturaMaxString() {
        return Integer.toString(temperaturaMax);
    }

    public void setTemperaturaMax(Integer temperaturaMax) {
        this.temperaturaMax = temperaturaMax;
    }

    public Integer getTemperaturaMin() {
        return temperaturaMin;
    }

    public String getTemperaturaMinString() {
        return Integer.toString(temperaturaMin);
    }

    public void setTemperaturaMin(Integer temperaturaMin) {
        this.temperaturaMin = temperaturaMin;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
