package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import java.util.Date;

public class taPublicacionesContent  implements Serializable{
  private int idRegPublicacionContent;
  private String idPublicacion;
  private String introNotarial;
  private String concluNotarial;
  private String concorNotarial;
  private String titDescripcion1;
  private String descripcion1;
  private String titDescripcion2;
  private String descripcion2;
  private String lugarJunta;
  private Date fecJunta;
  private String tipoJunta;
  private String ordenDia;

  public int getIdRegPublicacionContent() {
    return idRegPublicacionContent;
  }

  public void setIdRegPublicacionContent(int idRegPublicacionContent) {
    this.idRegPublicacionContent = idRegPublicacionContent;
  }

  public String getIdPublicacion() {
    return idPublicacion;
  }

  public void setIdPublicacion(String idPublicacion) {
    this.idPublicacion = idPublicacion;
  }

  public String getIntroNotarial() {
    return introNotarial;
  }

  public void setIntroNotarial(String introNotarial) {
    this.introNotarial = introNotarial;
  }

  public String getConcluNotarial() {
    return concluNotarial;
  }

  public void setConcluNotarial(String concluNotarial) {
    this.concluNotarial = concluNotarial;
  }

  public String getConcorNotarial() {
    return concorNotarial;
  }

  public void setConcorNotarial(String concorNotarial) {
    this.concorNotarial = concorNotarial;
  }

  public String getTitDescripcion1() {
    return titDescripcion1;
  }

  public void setTitDescripcion1(String titDescripcion1) {
    this.titDescripcion1 = titDescripcion1;
  }

  public String getDescripcion1() {
    return descripcion1;
  }

  public void setDescripcion1(String descripcion1) {
    this.descripcion1 = descripcion1;
  }

  public String getTitDescripcion2() {
    return titDescripcion2;
  }

  public void setTitDescripcion2(String titDescripcion2) {
    this.titDescripcion2 = titDescripcion2;
  }

  public String getDescripcion2() {
    return descripcion2;
  }

  public void setDescripcion2(String descripcion2) {
    this.descripcion2 = descripcion2;
  }

  public String getLugarJunta() {
    return lugarJunta;
  }

  public void setLugarJunta(String lugarJunta) {
    this.lugarJunta = lugarJunta;
  }

  public Date getFecJunta() {
    return fecJunta;
  }

  public void setFecJunta(Date fecJunta) {
    this.fecJunta = fecJunta;
  }

  public String getTipoJunta() {
    return tipoJunta;
  }

  public void setTipoJunta(String tipoJunta) {
    this.tipoJunta = tipoJunta;
  }

  public String getOrdenDia() {
    return ordenDia;
  }

  public void setOrdenDia(String ordenDia) {
    this.ordenDia = ordenDia;
  }
}
