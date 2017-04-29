package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.TbInCiiuDao;
import bo.gaceta.rcb.modelo.TbInCiiu;
import java.io.Serializable;
import java.util.List;

public class TbInCiiuBll implements Serializable {

  private final TbInCiiuDao CiiuDao = new TbInCiiuDao();
  private Object FacesContext;

  public List<TbInCiiu> list() throws Exception {
    return CiiuDao.list();
  }

  public List<TbInCiiu> listFiltroClase(String txtClase) throws Exception {
    return CiiuDao.listFiltroClase(txtClase);
  }

  public List<TbInCiiu> listSeccion() throws Exception {
    return CiiuDao.listSeccion();
  }

  public List<TbInCiiu> listDivision(String idSeccion) throws Exception {
    return CiiuDao.listDivision(idSeccion);
  }

  public List<TbInCiiu> listGrupo(String idDivision) throws Exception {
    return CiiuDao.listGrupo(idDivision);
  }

  public List<TbInCiiu> listClaseByGroup(String idGrupo) throws Exception {
    return CiiuDao.listClasebyGroup(idGrupo);
  }
  
  public List<TbInCiiu> listClaseBySeccion(String idSeccion) throws Exception {
    return CiiuDao.listClasebySeccion(idSeccion);
  }
  
  public List<TbInCiiu> listClaseByDivision(String idDivision) throws Exception {
    return CiiuDao.listClasebyDivision(idDivision);
  }

  public  String getDescCLaseCIIU(String idClase){
    return CiiuDao.desClase(idClase);

  }
  
}
