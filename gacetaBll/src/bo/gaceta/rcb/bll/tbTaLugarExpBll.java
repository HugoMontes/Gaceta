package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.tbTaLugarExpDao;
import bo.gaceta.rcb.modelo.tbTaLugarExp;
import java.io.Serializable;
import java.util.List;

public class tbTaLugarExpBll implements Serializable {

  private static final long serialVersionUID = 5988758555959241346L;
  private final tbTaLugarExpDao lugarDao = new tbTaLugarExpDao();
  private Object FacesContext;

  public List<tbTaLugarExp> list() throws Exception {
    return lugarDao.list();
  }

  public tbTaLugarExp listByIdLugarExp(Integer idLugar) throws Exception {
    tbTaLugarExp resLugar = lugarDao.getById(idLugar);
    if (resLugar == null) {
      throw new Exception("El código de lugar de expedicion no existe");
    }
    return resLugar;
  }
}
