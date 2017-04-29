package bo.gaceta.rcb.bll;

import bo.gaceta.rcb.dao.taCapitalDao;
import bo.gaceta.rcb.modelo.taCapital;

import java.io.Serializable;

public class taCapitalBll implements Serializable {
  private final taCapitalDao CapitalDao = new taCapitalDao();
  public void add(taCapital obj) throws Exception {
    try{
      CapitalDao.add(obj);
    }
    catch (Exception ex){
      System.console().printf(ex.getMessage());
    }
  }


  public void update(taCapital obj) throws Exception {
    try{
      CapitalDao.update(obj);
    }
    catch (Exception ex){
      System.console().printf(ex.getMessage());
    }
  }

  public taCapital getIdEmpresa(String IdEmpresa) throws Exception {
    taCapital resEmpresa = CapitalDao.getByIdEmpresa(IdEmpresa);
    return resEmpresa;
  }

  public  boolean verifByIdEmpresa(String IdEmpresa)
  {
    boolean resp=CapitalDao.verifByIdEmpresa(IdEmpresa);
    return resp;
  }

}
