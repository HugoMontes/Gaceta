
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
  
}
