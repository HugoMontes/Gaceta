
package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import lombok.Data;


@Data
public class tbInMatriculas implements Serializable {
  
  private static final long serialVersionUID = -7299193637665618784L;
  private String  idMatricula;
  private String razonSocial;
  private Integer tipoSocietario;
  private String correoElectronico;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getIdMatricula() {
    return idMatricula;
  }

  public void setIdMatricula(String idMatricula) {
    this.idMatricula = idMatricula;
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public Integer getTipoSocietario() {
    return tipoSocietario;
  }

  public void setTipoSocietario(Integer tipoSocietario) {
    this.tipoSocietario = tipoSocietario;
  }

  public String getCorreoElectronico() {
    return correoElectronico;
  }

  public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
  }
}
