package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.tbInMatriculasDao;
import bo.gaceta.rcb.modelo.tbInMatriculas;
import com.google.common.base. Strings;
import java.io.Serializable;


public class tbInMatriculasBll implements Serializable {
    private final tbInMatriculasDao matriculaDao = new tbInMatriculasDao();
    private Object FacesContext;
  
  
  private static final long serialVersionUID = 1855956294494739996L;
  
  
  public tbInMatriculas listByIdMatricula(String  idMatricula) throws Exception {
    String leftPaddedString = Strings.padStart(idMatricula, 8, '0');
    if (idMatricula.length()<8) {
      idMatricula=Strings.padStart(idMatricula, 8, '0');
    }
    
    tbInMatriculas dtMatricula = matriculaDao.getById(idMatricula);
    if (dtMatricula == null) {
      throw new Exception("La Matricula no se encuentra registrada");
    }
    return dtMatricula;
  }

}
