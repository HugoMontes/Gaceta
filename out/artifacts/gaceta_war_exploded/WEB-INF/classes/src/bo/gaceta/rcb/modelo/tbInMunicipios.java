package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import lombok.Data;


@Data
public class tbInMunicipios implements Serializable{

  private String idMunicipio;
  private String municipio;
  private String idDepartamento;
  private String departamento;
  private String idProvincia;
  private String provincia;


  public String getIdMunicipio() {
    return idMunicipio;
  }

  public void setIdMunicipio(String idMunicipio) {
    this.idMunicipio = idMunicipio;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public String getIdDepartamento() {
    return idDepartamento;
  }

  public void setIdDepartamento(String idDepartamento) {
    this.idDepartamento = idDepartamento;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  public String getIdProvincia() {
    return idProvincia;
  }

  public void setIdProvincia(String idProvincia) {
    this.idProvincia = idProvincia;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }
}
