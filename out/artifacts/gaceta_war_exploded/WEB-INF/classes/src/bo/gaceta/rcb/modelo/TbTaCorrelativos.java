package bo.gaceta.rcb.modelo;

import java.io.Serializable;
import lombok.Data;

@Data
public class TbTaCorrelativos  implements Serializable{
  
  private static final long serialVersionUID = -7263354208550428420L;
  private Integer idCorrelativo;
  private String desCorrelativo;
  private Integer nroCorrelativo;
  private String pref;

  public Integer getIdCorrelativo() {
    return idCorrelativo;
  }

  public void setIdCorrelativo(Integer idCorrelativo) {
    this.idCorrelativo = idCorrelativo;
  }

  public String getDesCorrelativo() {
    return desCorrelativo;
  }

  public void setDesCorrelativo(String desCorrelativo) {
    this.desCorrelativo = desCorrelativo;
  }

  public Integer getNroCorrelativo() {
    return nroCorrelativo;
  }

  public void setNroCorrelativo(Integer nroCorrelativo) {
    this.nroCorrelativo = nroCorrelativo;
  }

  public String getPref() {
    return pref;
  }

  public void setPref(String pref) {
    this.pref = pref;
  }
}
