package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import lombok.Data;


public class tbTaTipoSocietario implements Serializable{
  
  private Integer idTipoSocietario;
  private String tipoSocietario;
  private String ctrShowReg;

  public Integer getIdTipoSocietario() {
    return idTipoSocietario;
  }

  public void setIdTipoSocietario(Integer idTipoSocietario) {
    this.idTipoSocietario = idTipoSocietario;
  }

  public String getTipoSocietario() {
    return tipoSocietario;
  }

  public void setTipoSocietario(String tipoSocietario) {
    this.tipoSocietario = tipoSocietario;
  }

  public String getCtrShowReg() {
    return ctrShowReg;
  }

  public void setCtrShowReg(String ctrShowReg) {
    this.ctrShowReg = ctrShowReg;
  }
}
