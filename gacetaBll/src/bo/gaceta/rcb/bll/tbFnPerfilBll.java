
package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.tbFnPerfilDao;
import bo.gaceta.rcb.modelo.tbFnPerfil;
import java.io.Serializable;
import java.util.List;


public class tbFnPerfilBll implements Serializable {
  
  private static final long serialVersionUID = 3377180916472680648L;
  private final tbFnPerfilDao perfilDao = new tbFnPerfilDao();
  
   public List<tbFnPerfil> list() throws Exception {
    return perfilDao.list();
  }
   
     public List<tbFnPerfil> listByPerfil(Integer idPerfil) throws Exception {
    return perfilDao.listByPerfil(idPerfil);
  }
   
}
