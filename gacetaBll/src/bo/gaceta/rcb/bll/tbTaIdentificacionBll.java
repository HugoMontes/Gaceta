package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.tbTaIdentificacionDao;
import bo.gaceta.rcb.modelo.tbTaIdentificacion;
import java.io.Serializable;
import java.util.List;

public class tbTaIdentificacionBll implements Serializable {

  private final tbTaIdentificacionDao identDao = new tbTaIdentificacionDao();
  private Object FacesContext;

  public List<tbTaIdentificacion> list() throws Exception {
    return identDao.list();
  }
  
  public tbTaIdentificacion listByIdIdent(Integer idIdent) throws Exception {
    tbTaIdentificacion resIdent = identDao.getById(idIdent);
    if (resIdent == null) {
      throw new Exception("El código de Identificacion no existe");
    }
    return resIdent;
  }
}
