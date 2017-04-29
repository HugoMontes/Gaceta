package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import lombok.Data;


public class tbTaLugarExp implements Serializable {

  private static final long serialVersionUID = 3096091347065776755L;

  private Integer idLugarExpedicion;
  private String lugarExpedicion;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Integer getIdLugarExpedicion() {
    return idLugarExpedicion;
  }

  public void setIdLugarExpedicion(Integer idLugarExpedicion) {
    this.idLugarExpedicion = idLugarExpedicion;
  }

  public String getLugarExpedicion() {
    return lugarExpedicion;
  }

  public void setLugarExpedicion(String lugarExpedicion) {
    this.lugarExpedicion = lugarExpedicion;
  }
}
