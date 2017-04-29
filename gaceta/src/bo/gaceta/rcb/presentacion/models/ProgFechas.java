package bo.gaceta.rcb.presentacion.models;

import java.io.Serializable;
import java.util.Date;


public class ProgFechas implements Serializable {
    private String idPublicacion;
    private Date fecPublicacion;

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Date getFecPublicacion() {
        return fecPublicacion;
    }

    public void setFecPublicacion(Date fecPublicacion) {
        this.fecPublicacion = fecPublicacion;
    }
}
