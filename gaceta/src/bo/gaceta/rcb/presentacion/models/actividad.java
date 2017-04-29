package bo.gaceta.rcb.presentacion.models;

import java.io.Serializable;


public class actividad implements Serializable {
    private String idClase;
    private String clase;

    public String getIdClase() {
        return idClase;
    }

    public void setIdClase(String idClase) {
        this.idClase = idClase;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
}
