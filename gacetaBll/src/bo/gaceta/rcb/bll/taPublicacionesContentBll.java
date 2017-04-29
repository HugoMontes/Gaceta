package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.taPublicacionesContentDao;
import bo.gaceta.rcb.modelo.taPublicacionesContent;

import java.io.Serializable;

public class taPublicacionesContentBll implements Serializable{
  private final taPublicacionesContentDao PublicaDao = new taPublicacionesContentDao();
  private Object FacesContext;

  public taPublicacionesContent datosPublica(int idPubCont) throws Exception {
    return PublicaDao.getByID(idPubCont);
  }

  public boolean altaPublicaContente(taPublicacionesContent obj) throws Exception {
    return PublicaDao.add(obj);
  }

}
