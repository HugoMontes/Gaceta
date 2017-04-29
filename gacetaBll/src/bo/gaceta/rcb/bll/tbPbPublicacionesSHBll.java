package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.tbPbPublicacionesSHDao;
import bo.gaceta.rcb.modelo.tbPbPublicaciones;
import java.util.Date;
import java.util.List;

public class tbPbPublicacionesSHBll {

  private final tbPbPublicacionesSHDao PublicaDao = new tbPbPublicacionesSHDao();
  private Object FacesContext;

  public List<tbPbPublicaciones> list() throws Exception {
    return PublicaDao.list();
  }

  public List<tbPbPublicaciones> listPubToday() throws Exception {
    return PublicaDao.listToday();
  }

  public List<tbPbPublicaciones> listPubFiltro(Date dateFiltra,Date dateFiltraFin) throws Exception {
    return PublicaDao.listPubFiltro(dateFiltra,dateFiltraFin);
  }

  public List<tbPbPublicaciones> listPubFiltroTxt(String txtFiltro) throws Exception {
    return PublicaDao.listPubFiltroTxt(txtFiltro);
  }

  public List<tbPbPublicaciones> listPubMatricula(String txtFiltro) throws Exception {
    return PublicaDao.listPubFiltroMatricula(txtFiltro);
  }
  
   public tbPbPublicaciones getByIdPub(String idPublicacion) throws Exception {
    return PublicaDao.getByIdPub(idPublicacion);
  }
  
}
