package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.tbInMunicipiosDao;
import bo.gaceta.rcb.modelo.tbInMunicipios;
import java.io.Serializable;
import java.util.List;

public class tbInMunicipiosBll implements Serializable {

  private final tbInMunicipiosDao MunipDao = new tbInMunicipiosDao();
  private Object FacesContext;

  public tbInMunicipios getById(String id){
    return  MunipDao.getById(id);
  }

  public List<tbInMunicipios> listByDpto(String codDpto) throws Exception{
    return MunipDao.getByDpto(codDpto);
  }

  public String desByIdMunip(String idMunip){
    return  MunipDao.desMunicipio(idMunip);
  }

}
