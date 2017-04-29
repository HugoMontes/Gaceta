package bo.gaceta.rcb.modelo;

import java.io.Serializable;


public class tbTaIdentificacion implements Serializable {

  private Integer idTipoIdentificacion;
  private String tipoIdentificacion;

  public Integer getIdTipoIdentificacion() {
    return idTipoIdentificacion;
  }

  public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
    this.idTipoIdentificacion = idTipoIdentificacion;
  }

  public String getTipoIdentificacion() {
    return tipoIdentificacion;
  }

  public void setTipoIdentificacion(String tipoIdentificacion) {
    this.tipoIdentificacion = tipoIdentificacion;
  }
}
