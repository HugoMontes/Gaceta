package bo.gaceta.rcb.modelo;

import lombok.Data;
import java.io.Serializable;


public class tbTipoSocab implements Serializable {
    private int idCodab;
    private int idTipoSocietario;
    private String abreviacion;

    public int getIdCodab() {
        return idCodab;
    }

    public void setIdCodab(int idCodab) {
        this.idCodab = idCodab;
    }

    public int getIdTipoSocietario() {
        return idTipoSocietario;
    }

    public void setIdTipoSocietario(int idTipoSocietario) {
        this.idTipoSocietario = idTipoSocietario;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }
}
