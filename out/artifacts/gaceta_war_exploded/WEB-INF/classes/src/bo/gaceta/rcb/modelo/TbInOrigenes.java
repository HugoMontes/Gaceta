
package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import lombok.Data;

public class TbInOrigenes implements Serializable {
    private int idOrigen;
    private String origen;

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
}
