package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import lombok.Data;

@Data
public class TbInCiiu implements Serializable {
  private String idSeccion;
  private String seccion;
  private String idDivision;
  private String division;
  private String idGrupo;
  private String grupo;
  private String idClase;
  private String clase;

  public String getIdSeccion() {
    return idSeccion;
  }

  public void setIdSeccion(String idSeccion) {
    this.idSeccion = idSeccion;
  }

  public String getSeccion() {
    return seccion;
  }

  public void setSeccion(String seccion) {
    this.seccion = seccion;
  }

  public String getIdDivision() {
    return idDivision;
  }

  public void setIdDivision(String idDivision) {
    this.idDivision = idDivision;
  }

  public String getDivision() {
    return division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public String getIdGrupo() {
    return idGrupo;
  }

  public void setIdGrupo(String idGrupo) {
    this.idGrupo = idGrupo;
  }

  public String getGrupo() {
    return grupo;
  }

  public void setGrupo(String grupo) {
    this.grupo = grupo;
  }

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
